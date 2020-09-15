package services.financials.Impl

import com.websudos.phantom.dsl.ResultSet
import conf.util.MarginKeys
import domain.financials.{CustomerRejectedUploads, CustomerUploads, OrganisationFinancialUploadsEvents}
import repositories.financials.CustomerRejectedUploadsRepository
import services.Service
import services.financials.{CustomerRejectedUploadsService, FileUploadPostService, OrganisationFinancialUploadsEventsService}
import services.util.ApiKeysService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/17.
  */
class CustomerRejectedUploadsServiceImpl extends CustomerRejectedUploadsService with Service {

  override def saveOrCustomer(customeruploads: CustomerRejectedUploads): Future[ResultSet] = {

    CustomerRejectedUploadsRepository.save(customeruploads)
  }

  override def getUploadByOrganisation(orgCode: String): Future[Seq[CustomerRejectedUploads]] = {

    CustomerRejectedUploadsRepository.getUploadByOrganisation(orgCode)
  }

  override def getUploadsYear(orgCode: String, year: Int): Future[Seq[CustomerRejectedUploads]] = {
    CustomerRejectedUploadsRepository.getUploadsYear(orgCode, year)
  }

  override def getUploadsByMonth(orgCode: String, year: Int, month: Int): Future[Seq[CustomerRejectedUploads]] = {
    CustomerRejectedUploadsRepository.getUploadsByMonth(orgCode, year, month)
  }

  override def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String): Future[Option[CustomerRejectedUploads]] = {
    CustomerRejectedUploadsRepository.getUploadeByCode(orgCode, year, month, accountingCode)
  }

  override def getCustomerUploadsByFileId(orgCode: String, fileId: String): Future[Seq[CustomerRejectedUploads]] = {

    CustomerRejectedUploadsRepository.getUploadByOrganisation(orgCode) map (custup => custup.filter(customerUpload => customerUpload.fileId.equalsIgnoreCase(fileId)))

  }

  override def saveEvent(custuploads: Seq[CustomerUploads], event: OrganisationFinancialUploadsEvents): Future[String] = {
    if (event.status == "REJECTED") {
      custuploads foreach (custupload =>
        CustomerRejectedUploadsRepository.save(CustomerRejectedUploads(
          orgCode = custupload.orgCode,
          reference = custupload.reference,
          date = custupload.date,
          accountingCode = custupload.accountingCode,
          fileId = custupload.fileId,
          year = custupload.year,
          month = custupload.month,
          day = custupload.day,
          accountingSystem = custupload.accountingSystem,
          debitValue = custupload.debitValue,
          creditValue = custupload.creditValue,
          entryCategory = custupload.entryCategory,
          entrySubCategory = custupload.entrySubCategory,
          entryDescription = custupload.entryDescription,
          txnType = custupload.txnType,
          csvStringInput = custupload.csvStringInput,
          mappingCode = custupload.mappingCode
        ))
        )
      val cust = custuploads.head
        ApiKeysService.apply.get(MarginKeys.GO_URL) map ( url => {
        FileUploadPostService.apply.processRejection(url.head.value,cust.orgCode,cust.year,cust.month)
      })
      OrganisationFinancialUploadsEventsService.apply.save(event)

    } else if(event.status == "AUTHORISED"){
      val cust = custuploads.head
      ApiKeysService.apply.get(MarginKeys.GO_URL) map ( url => {
        FileUploadPostService.apply.processRejection(url.head.value,cust.orgCode,cust.year,cust.month)
      })

      OrganisationFinancialUploadsEventsService.apply.save(event)


    } else {
      // Call Go
      val cust = custuploads.head
      ApiKeysService.apply.get(MarginKeys.GO_URL) map ( url => {
        FileUploadPostService.apply.processApproval(url.head.value,cust.orgCode,cust.year,cust.month)
      })

      OrganisationFinancialUploadsEventsService.apply.save(event)

    }

    Future {
      "Done"
    }
  }
}
