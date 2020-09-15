package services.organisation.impl

import com.websudos.phantom.dsl._
import conf.security.AuthUtil
import conf.util.Util
import domain.organisation.OrganisationLocation
//import domain.organisation.Organisation
//import domain.people.User
import repositories.organisation.OrganisationLocationRepository
//import repositories.people.UserRepository
import services.Service
import services.organisation.OrganisationLocationServices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class OrganisationLocationServicesImpl extends OrganisationLocationServices with Service{


  override def save(location: OrganisationLocation): Future[ResultSet]={
    OrganisationLocationRepository.save(location)
  }
  override def getLocationById(orgCode:String, date: DateTime):Future[Option[OrganisationLocation]]={
    OrganisationLocationRepository.getLocationById(orgCode,date)
  }
  override def getLocations(orgCode:String) : Future[Seq[OrganisationLocation]]={
    OrganisationLocationRepository.getLocations(orgCode)
  }
}
