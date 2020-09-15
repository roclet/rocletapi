package services.financials.Impl

import com.websudos.phantom.dsl._
import domain.financials.{CustomerUploads, FinanceStatementCategoryCodeMapping}
import repositories.financials.CustomerUploadsRepository
import repositories.financials.FinanceStatementCategoryCodeMappingRepository
import services.Service
import services.financials.CustomerUploadsService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/27. FIX FIX FIIX
  */
@Deprecated
class CustomerUploadsServiceImpl extends CustomerUploadsService with Service{
  override def saveOrCustomer(customeruploads:CustomerUploads):Future[ResultSet]={

    CustomerUploadsRepository.save(customeruploads)
  }
  override def getUploadByOrganisation(orgCode: String):Future[Seq[CustomerUploads]]={

    CustomerUploadsRepository.getUploadByOrganisation(orgCode)
  }
  override def getUploadsYear(orgCode: String, year: Int): Future[Seq[CustomerUploads]]={
    CustomerUploadsRepository.getUploadsYear(orgCode,year)
  }
  override def getUploadsByMonth(orgCode: String, year: Int, month: Int): Future[Seq[CustomerUploads]]={
    CustomerUploadsRepository.getUploadsByMonth(orgCode,year,month)
  }
  override def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String): Future[Option[CustomerUploads]]={
    CustomerUploadsRepository.getUploadeByCode(orgCode,year,month,accountingCode)
  }
  override def saveOrFinance(financestatementcategory:FinanceStatementCategoryCodeMapping):Future[ResultSet]={
    FinanceStatementCategoryCodeMappingRepository.save(financestatementcategory)
  }
  override def getIncomeStatementCategoryById(orgCode:String,id:String):Future[Seq[FinanceStatementCategoryCodeMapping]]={
    FinanceStatementCategoryCodeMappingRepository.getCategoriesByFinType(orgCode,id)
  }
  override def getIncomeStatementCategoryBy(orgCode:String):Future[Seq[FinanceStatementCategoryCodeMapping]]={
    FinanceStatementCategoryCodeMappingRepository.getOrgCategories(orgCode)
  }

  override def getCustomerUploadsByFileId(orgCode: String, fileId: String): Future[Seq[CustomerUploads]] = {
  CustomerUploadsRepository.getUploadByOrganisation(orgCode) map ( custup => custup.filter( customerUpload => customerUpload.fileId.equalsIgnoreCase(fileId)))
}

  override def doesUploadExist(orgCode: String, year: Int, month: Int): Future[Boolean] = {
    getUploadsByMonth(orgCode, year, month) map ( uploads => uploads.size < 1 )
  }
}
