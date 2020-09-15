package services.organisation.impl

import com.websudos.phantom.dsl
import com.websudos.phantom.dsl.ResultSet
import conf.security.{AuthUtil, RolesID}
import conf.util.{MarginKeys, Util}
import domain.organisation.{Organisation, OrganisationStatus}
import domain.people.{User, UserRole}
import repositories.organisation.{OrganisationRepository, OrganisationStatusRepository}
import repositories.people.{UserRepository, UserRoleRepository}
import services.Service
import services.mail.MailPasswordService
import services.organisation.OrganisationServices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by hashcode on 2016/09/03.
  */
class OrganisationServicesImpl extends OrganisationServices with Service {
  override def generateOrganisationCode(organisationName: String): Future[String] = {
    val orgCode = Util.codeGen(organisationName)
    checkCodeAvailability(orgCode) map (result => {
      if (result) Util.codeGen(organisationName)
      else orgCode
    })
  }

  override def checkCodeAvailability(orgCode: String): Future[Boolean] = {
    getOrganisationByCode(orgCode: String) map {
      case Some(organisation) => true
      case None => false
    }
  }

  override def getOrganisationByCode(code: String): Future[Option[Organisation]] = {
    OrganisationRepository.getOrganisationByCode(code)//.filter(org=>org.get.status.equalsIgnoreCase(MarginKeys.ACTIVE))
  }

  override def createOrganisation(organisation: Organisation): Future[dsl.ResultSet] = {
    for {
      results <- OrganisationRepository.save(organisation)
    } yield results
  }

  override def updateOrganisation(organisation: Organisation): Future[dsl.ResultSet] = {
      OrganisationRepository.save(organisation)
  }


  override def getOrganisations(startValue: Int): Future[Iterator[Organisation]] = {
    OrganisationRepository.getAllOrganisations(startValue)
  }

  override def createOrganisationAdmin(organisation: Organisation): User = {
    User(organisation.contactEmail,
      "System",
      "Administrator",
      "Admin", Util.md5Hash(AuthUtil.generateRandomPassword()), "ACTIVE", organisation.orgCode)
  }

  override def getOrganisationStatus(orgCode: String): Future[Seq[OrganisationStatus]] = {
    OrganisationStatusRepository.getOrganisationStatus(orgCode)
  }

  override def craeteOrganisationStatus(status: OrganisationStatus): Future[ResultSet] = {
    OrganisationStatusRepository.save(status)
  }

  override def activateOrganisation(organisation: Organisation): Future[ResultSet] = {
    val admin = createOrganisationAdmin(organisation)
    val role = UserRole(admin.orgCode,admin.email,RolesID.ORG_ADMIN)
    for {
      results <- OrganisationRepository.save(organisation)
      results <- UserRepository.save(admin)
      saverole <-UserRoleRepository.save(role)
      mailesults <-MailPasswordService.apply.sendMail(admin)
    } yield results
  }
}
