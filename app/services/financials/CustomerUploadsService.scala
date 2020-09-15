package services.financials

import com.websudos.phantom.dsl._
import domain.financials.{CustomerUploads, FinanceStatementCategoryCodeMapping}
import services.financials.Impl.CustomerUploadsServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/22.
  */
trait CustomerUploadsService {

  def saveOrCustomer(customeruploads: CustomerUploads): Future[ResultSet]
  def getUploadByOrganisation(orgCode: String): Future[Seq[CustomerUploads]]
  def getUploadsYear(orgCode: String, year: Int): Future[Seq[CustomerUploads]]
  def getUploadsByMonth(orgCode: String, year: Int, month: Int): Future[Seq[CustomerUploads]]
  def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String): Future[Option[CustomerUploads]]
  def saveOrFinance(financestatementcategory:FinanceStatementCategoryCodeMapping):Future[ResultSet]
  def getIncomeStatementCategoryById(orgCode:String,id:String):Future[Seq[FinanceStatementCategoryCodeMapping]]
  def getIncomeStatementCategoryBy(orgCode:String):Future[Seq[FinanceStatementCategoryCodeMapping]]
  def getCustomerUploadsByFileId(orgCode: String, fileId: String) : Future[Seq[CustomerUploads]]
  def doesUploadExist(orgCode:String, year:Int, month:Int): Future[Boolean]

}
object CustomerUploadsService{


  def apply: CustomerUploadsService = new CustomerUploadsServiceImpl()
}
