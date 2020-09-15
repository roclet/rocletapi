package services.financials

import com.websudos.phantom.dsl._
import domain.financials.OrganisationFinancialUploadsEvents
import services.financials.Impl.OrganisationFinancialUploadsEventsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
trait OrganisationFinancialUploadsEventsService {

  def save(orgFinEvents: OrganisationFinancialUploadsEvents): Future[ResultSet]

  def getUploadsEvent(fileId: String): Future[Seq[OrganisationFinancialUploadsEvents]]
//  def truncateOrganisationFinancialUploadsEvents(): Future[ResultSet]
  def getAllUploadedFileStatusEvent: Future[Seq[OrganisationFinancialUploadsEvents]]
}
object OrganisationFinancialUploadsEventsService {
  def apply: OrganisationFinancialUploadsEventsService = new OrganisationFinancialUploadsEventsServiceImpl()
}
