package services.financials

import java.io.File

import domain.organisation.UploadMetaData
import domain.util.FileResults
import services.financials.Impl.FileUploadPostServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/14.
  */
trait FileUploadPostService {
  def processFile(file: File, uploadMetaData: UploadMetaData): Future[String]

  def storeFile(file: File, uploadMetaData: UploadMetaData): Future[FileResults]

  def processApproval(url: String, orgcode: String, year: Int, month: Int): Future[String]

  def processRejection(url: String, orgcode: String, year: Int, month: Int): Future[String]
}

object FileUploadPostService {
  def apply: FileUploadPostService = new FileUploadPostServiceImpl()
}
