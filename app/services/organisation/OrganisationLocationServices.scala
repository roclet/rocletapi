package services.organisation

import com.websudos.phantom.dsl._
import domain.organisation.OrganisationLocation
import services.organisation.impl.OrganisationLocationServicesImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
trait OrganisationLocationServices {
    def save(location: OrganisationLocation): Future[ResultSet]
    def getLocationById(orgCode:String, date: DateTime):Future[Option[OrganisationLocation]]
    def getLocations(orgCode:String) : Future[Seq[OrganisationLocation]]
}
object OrganisationLocationServices {
  def apply(): OrganisationLocationServices = new OrganisationLocationServicesImpl()
}
