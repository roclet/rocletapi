package services.organisation

import com.websudos.phantom.dsl._
import domain.organisation.OrganisationSubscriptions
import services.organisation.impl.OrganisationSubscriptionsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
trait OrganisationSubscriptionsService {
  def save(organisationsubscr: OrganisationSubscriptions): Future[ResultSet]

  def getBysubscriptionsId(orgCode: String, subscriptionsId: String, id:String): Future[Option[OrganisationSubscriptions]]

  def getOrganisationsSubscriptions(orgCode: String): Future[Seq[OrganisationSubscriptions]]
}

object OrganisationSubscriptionsService{
  def apply(): OrganisationSubscriptionsService = new OrganisationSubscriptionsServiceImpl()
}
