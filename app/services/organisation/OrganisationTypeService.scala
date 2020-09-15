package services.organisation

import com.websudos.phantom.dsl._
import domain.organisation.OrganisationType
import services.organisation.impl.OrganisationTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
trait OrganisationTypeService {
  def save(organisationtype: OrganisationType): Future[ResultSet]

  def getByOrganisationTypeId(id: String): Future[Option[OrganisationType]]

  def getAllOrg: Future[Seq[OrganisationType]]

}

object OrganisationTypeService {
  def apply(): OrganisationTypeService = new OrganisationTypeServiceImpl()
}
