package services.organisation.impl

import com.websudos.phantom.dsl.ResultSet
import domain.organisation.OrganisationSubscriptions
import repositories.organisation.OrganisationSubscrRepository
import services.Service
import services.organisation.OrganisationSubscriptionsService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class OrganisationSubscriptionsServiceImpl extends OrganisationSubscriptionsService with Service{
  override def save(organisationsubscr: OrganisationSubscriptions): Future[ResultSet] = {
    OrganisationSubscrRepository.save(organisationsubscr)
  }

  override def getBysubscriptionsId(orgCode: String, subscriptionsId: String, id:String): Future[Option[OrganisationSubscriptions]] = {
    OrganisationSubscrRepository.getOrgSubcriptById(orgCode,subscriptionsId,id)
  }

  override def getOrganisationsSubscriptions(orgCode: String): Future[Seq[OrganisationSubscriptions]] = {
    OrganisationSubscrRepository.findAll(orgCode)
  }
}
