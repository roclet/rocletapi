package services.organisation.impl

import com.websudos.phantom.dsl.ResultSet
import domain.organisation.OrganisationType
import repositories.organisation.OrganisationTypeRepository
import services.Service
import services.organisation.OrganisationTypeService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class OrganisationTypeServiceImpl extends OrganisationTypeService with Service{
  override def save(organisationtype: OrganisationType): Future[ResultSet] = {
    OrganisationTypeRepository.save(organisationtype)
  }

  override def getByOrganisationTypeId(id: String): Future[Option[OrganisationType]] = {
    OrganisationTypeRepository.getByOrganisationTypeId(id)
  }

  override def getAllOrg(): Future[Seq[OrganisationType]] = {
    OrganisationTypeRepository.getAllOrg()
  }
}
