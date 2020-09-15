package services.organisation.impl

import com.websudos.phantom.dsl.ResultSet
import domain.organisation.OrganisationDocuments
import repositories.organisation.OrganisationDocRepository
import services.Service
import services.organisation.OrganisationDocumentServices

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class OrganisationDocumentServicesImpl extends OrganisationDocumentServices with Service{
  override def save(organisationdoc: OrganisationDocuments): Future[ResultSet] = {
    OrganisationDocRepository.save(organisationdoc)
  }
  override def findById(orgCode: String, url: String): Future[Option[OrganisationDocuments]] = {
    OrganisationDocRepository.findById(orgCode, url)
  }
  override def getOrganisationsDocByCode(orgCode: String): Future[Seq[OrganisationDocuments]] = {
    OrganisationDocRepository.getOrganisationsDocByCode(orgCode)
  }
}
