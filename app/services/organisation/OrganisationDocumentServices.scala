package services.organisation

import com.websudos.phantom.dsl._
import domain.organisation.OrganisationDocuments
import services.organisation.impl.OrganisationDocumentServicesImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
trait OrganisationDocumentServices {
  def save(organisationdoc: OrganisationDocuments): Future[ResultSet]

  def findById(orgCode: String, url: String): Future[Option[OrganisationDocuments]]

  def getOrganisationsDocByCode(orgCode: String): Future[Seq[OrganisationDocuments]]
}

object OrganisationDocumentServices {
  def apply(): OrganisationDocumentServices = new OrganisationDocumentServicesImpl()
}
