package services.financials.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.financials.OrganisationFinancialUploadsEvents
import repositories.financials.OrganisationFinancialUploadsEventsRepository
import services.Service
import services.financials.OrganisationFinancialUploadsEventsService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class OrganisationFinancialUploadsEventsServiceImpl extends OrganisationFinancialUploadsEventsService with Service{
  override def save(orgFinEvents: OrganisationFinancialUploadsEvents): Future[ResultSet] = {
    OrganisationFinancialUploadsEventsRepository.save(orgFinEvents)
  }

  override def getUploadsEvent(fileId: String): Future[Seq[OrganisationFinancialUploadsEvents]] = {
    OrganisationFinancialUploadsEventsRepository.getUploadsEvent(fileId)
  }


  override def getAllUploadedFileStatusEvent: Future[Seq[OrganisationFinancialUploadsEvents]]={
    OrganisationFinancialUploadsEventsRepository.getAllUploadedFileStatusEvents
  }
}
