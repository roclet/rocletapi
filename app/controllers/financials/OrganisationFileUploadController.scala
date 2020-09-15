package controllers.financials

import java.io.File
import java.nio.file.attribute.PosixFilePermission._
import java.nio.file.attribute.PosixFilePermissions
import java.nio.file.{Files, Path}
import java.text.SimpleDateFormat
import java.util

import akka.stream.IOResult
import akka.stream.scaladsl._
import akka.util.ByteString
import conf.security.{LoggedInUser, TokenCheck}
import conf.util.MarginKeys
import domain.financials.{OrganisationFinancialUploads, OrganisationFinancialUploadsEvents}
import domain.organisation.UploadMetaData
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.libs.streams._
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{Controller, _}
import play.core.parsers.Multipart.FileInfo
import services.financials._
import services.util.ApiKeysService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/13.
  */
class OrganisationFileUploadController extends Controller {
  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  type FilePartHandler[A] = FileInfo => Accumulator[ByteString, FilePart[A]]

  def streamUpload = Action.async(parse.multipartFormData(handleFilePartAsFile)) {
    request =>
      val dateParam = request.body.asFormUrlEncoded("date").head
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode")
      val fileOption = request.body.file("upload").map {
        case FilePart(key, filename, contentType, file) => {
          file
        }
      }
      val formatter = new SimpleDateFormat("yyyy-MM-dd")
      val date = formatter.parse(dateParam)

      val response = for {
        auth <- TokenCheck.getTokenForUpload(request)
        loggedInUser <- LoggedInUser.user(orgCode.get, email.get) //if auth.status == "VALID"
        cdnUrl <- ApiKeysService.apply.get(MarginKeys.CDN_URL)
        goUrl <- ApiKeysService.apply.get(MarginKeys.GO_URL)
        storedFile <- FileUploadPostService.apply.storeFile(fileOption.get, UploadMetaData(loggedInUser.get.email, date, tokenParam.getOrElse(""), loggedInUser.get.orgCode, cdnUrl.get.value, ""))
        saveFile <- OrganisationFinancialUploadsService.apply.save(OrganisationFinancialUploads(loggedInUser.get.orgCode, storedFile.id, storedFile.url, new DateTime, storedFile.mime))
        processFile <- FileUploadPostService.apply.processFile(fileOption.get, UploadMetaData(loggedInUser.get.email, date, tokenParam.getOrElse(""), loggedInUser.get.orgCode, goUrl.get.value, storedFile.id))
      } yield processFile

      response.map(resp => Ok(Json.toJson(resp)))
        .recover {
          case e: Exception => {
            println(e.getMessage())
            Unauthorized
          }
        }
  }

  def getOrganisationUploadedFiles(orgCode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationFinancialUploadsService.apply.getOrganisationUploadedFiles(orgCode) //if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            println(e.getStackTrace)
            Unauthorized
          }
        }
  }

  def getUploadedFile(orgCode: String, fileId: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationFinancialUploadsService.apply.getOrganisationUploadedFile(orgCode, fileId) //if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getUploadedFileStatusHistory(fileId: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationFinancialUploadsEventsService.apply.getUploadsEvent(fileId) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getUploadedFileStatus(fileId: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationFinancialUploadsEventsService.apply.getUploadsEvent(fileId) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result.head)))
        .recover { case e: Exception => Unauthorized }
  }

  def getAllUploadedFileStatus = Action.async {
    request =>
      val response = for {
      //        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationFinancialUploadsEventsService.apply.getAllUploadedFileStatusEvent
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  private def handleFilePartAsFile: FilePartHandler[File] = {
    case FileInfo(partName, filename, contentType) =>
      val attr = PosixFilePermissions.asFileAttribute(util.EnumSet.of(OWNER_READ, OWNER_WRITE))
      val path: Path = Files.createTempFile("multipartBody", "tempFile", attr)
      val file = path.toFile
      val fileSink: Sink[ByteString, Future[IOResult]] = FileIO.toPath(path)
      val accumulator: Accumulator[ByteString, IOResult] = Accumulator(fileSink)
      accumulator.map {
        case IOResult(count, status) =>
          logger.info(s"count = $count, status = $status")
          FilePart(partName, filename, contentType, file)
      }(play.api.libs.concurrent.Execution.defaultContext)
  }

  private def operateOnTempFile(file: File) = {
    val size = Files.size(file.toPath)
    logger.info(s"size = ${size}")
    Files.deleteIfExists(file.toPath)
    size
  }

  def getCustomerUploadsByFileId(orgCode: String, fileId: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- CustomerUploadsService.apply.getCustomerUploadsByFileId(orgCode, fileId) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }


  def saveFileEvents(orgCode: String) = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[OrganisationFinancialUploadsEvents](input).get
      val response = for {
        custuploads <- CustomerUploadsService.apply.getCustomerUploadsByFileId(orgCode, entity.fileId)
        saveEvents <- CustomerRejectedUploadsService.apply.saveEvent(custuploads, entity)
      } yield saveEvents
      response.map(result => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

}
