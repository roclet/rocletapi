package services.financials

import com.websudos.phantom.dsl.ResultSet
import domain.financials.{CustomerRejectedUploads, CustomerUploads, OrganisationFinancialUploadsEvents}

import scala.concurrent.ExecutionContext.Implicits.global
import repositories.financials.{CustomerRejectedUploadsRepository, CustomerUploadsRepository}
import services.financials.Impl.CustomerRejectedUploadsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/17.
  */
trait CustomerRejectedUploadsService {

  def getCustomerUploadsByFileId(orgCode: String, fileId: String):Future[Seq[CustomerRejectedUploads]]

  def saveEvent(custuploads: Seq[CustomerUploads],event:OrganisationFinancialUploadsEvents):Future[String]

  def saveOrCustomer(customeruploads: CustomerRejectedUploads): Future[ResultSet]

  def getUploadByOrganisation(orgCode: String): Future[Seq[CustomerRejectedUploads]]

  def getUploadsYear(orgCode: String, year: Int): Future[Seq[CustomerRejectedUploads]]

  def getUploadsByMonth(orgCode: String, year: Int, month: Int): Future[Seq[CustomerRejectedUploads]]

  def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String): Future[Option[CustomerRejectedUploads]]

}

object CustomerRejectedUploadsService {
  def apply: CustomerRejectedUploadsService = new CustomerRejectedUploadsServiceImpl()
}
