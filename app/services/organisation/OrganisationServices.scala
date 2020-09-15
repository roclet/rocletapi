package services.organisation

import com.websudos.phantom.dsl
import com.websudos.phantom.dsl._
import domain.organisation.{Organisation, OrganisationStatus}
import domain.people.User
import services.organisation.impl.OrganisationServicesImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/03.
  */
trait OrganisationServices {
  def activateOrganisation(organisation: Organisation): Future[dsl.ResultSet]


  def generateOrganisationCode(organisationName: String): Future[String]

  def checkCodeAvailability(orgCode: String): Future[Boolean]

  def getOrganisationByCode(orgCode: String): Future[Option[Organisation]]

  def createOrganisation(organisation: Organisation): Future[dsl.ResultSet]

  def updateOrganisation(organisation: Organisation): Future[dsl.ResultSet]

  def getOrganisations(startValue: Int): Future[Iterator[Organisation]]

  def createOrganisationAdmin(organisation: Organisation):User

  def getOrganisationStatus(orgCode: String): Future[Seq[OrganisationStatus]]

  def craeteOrganisationStatus(status: OrganisationStatus): Future[ResultSet]

}

object OrganisationServices {
  def apply(): OrganisationServices = new OrganisationServicesImpl
}

