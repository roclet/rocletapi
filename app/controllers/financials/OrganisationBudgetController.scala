package controllers.financials

import java.io.File
import java.nio.file.{Files, Path}
import java.nio.file.attribute.PosixFilePermission.{OWNER_READ, OWNER_WRITE}
import java.nio.file.attribute.PosixFilePermissions
import java.text.SimpleDateFormat
import java.util
import java.util.Date

import akka.stream.IOResult
import akka.stream.scaladsl.{FileIO, Sink}
import akka.util.ByteString
import conf.security.{LoggedInUser, TokenCheck}
import conf.util.MarginKeys
import domain.financials.{OrganisationBudget, OrganisationFinancialUploads, OrganisationFinancialUploadsEvents}
import domain.organisation.UploadMetaData
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.libs.streams.Accumulator
import play.api.mvc.MultipartFormData.FilePart
import play.api.mvc.{Action, Controller}
import play.core.parsers.Multipart.FileInfo
import services.financials.{BudgetService, FileUploadPostService, OrganisationFinancialUploadsEventsService, OrganisationFinancialUploadsService}
import services.organisation.OrganisationBudgetService
import services.util.ApiKeysService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2017/01/10.
  */
class OrganisationBudgetController extends Controller {
  private val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  type FilePartHandler[A] = FileInfo => Accumulator[ByteString, FilePart[A]]

  def saveOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[OrganisationBudget](input).get
      val results = OrganisationBudgetService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getOrganisationBudgets(orgCode: String) = Action.async {
    request =>
      OrganisationBudgetService.apply.getOrganisationBudgets(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }

  def getOrganisationBudgetByYear(orgCode: String, year: Int) = Action.async {
    request =>
      OrganisationBudgetService.apply.getOrganisationBudgetByYear(orgCode, year) map (result =>
        Ok(Json.toJson(result)))
  }

  def getBudgetSetItemWithValues(orgCode: String, year: Int) = Action.async {
    request =>
      OrganisationBudgetService.apply.getOrganisationBudgetByYear(orgCode, year) map (budgets => {
        val results = budgets.filter(budget => budget.status.equalsIgnoreCase("YES"))
        Ok(Json.toJson(results))
      })
  }

  def getBudgetItem(orgCode: String, year: Int, item: String) = Action.async {
    request =>
      OrganisationBudgetService.apply.getOrganisationBudgetItemByYear(orgCode, year, item) map (budgets => {
        Ok(Json.toJson(budgets))
      }
        )
  }

  def streamUpload = Action.async(parse.multipartFormData(handleFilePartAsFile)) {
    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode")
      val fileOption = request.body.file("upload").map {
        case FilePart(key, filename, contentType, file) => {
          file
        }
      }

      val response = for {
        auth <- TokenCheck.getTokenForUpload(request)
        loggedInUser <- LoggedInUser.user(orgCode.get, email.get) //if auth.status == "VALID"
        goUrl <- ApiKeysService.apply.get(MarginKeys.GO_URL)
        processFile <- BudgetService.apply.processFile(fileOption.get, UploadMetaData(loggedInUser.get.email, new Date(), tokenParam.getOrElse(""), loggedInUser.get.orgCode, goUrl.get.value, "node"))
      } yield processFile

      response.map(resp => Ok(Json.toJson(resp)))
        .recover {
          case e: Exception => {
            println(e.getMessage())
            Unauthorized
          }
        }
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

}
