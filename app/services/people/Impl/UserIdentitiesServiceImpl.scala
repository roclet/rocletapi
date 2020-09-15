package services.people.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.people.UserIdentities
import repositories.people.UserIdentitiesRepository
import services.Service
import services.people.UserIdentitiesService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class UserIdentitiesServiceImpl extends UserIdentitiesService with Service {

  override def save(useridentities: UserIdentities): Future[ResultSet] = {
    UserIdentitiesRepository.save(useridentities)
  }

  override def getUserIdentitiesByemail(orgCode:String,email:String): Future[Seq[UserIdentities]] = {
    UserIdentitiesRepository.getUserIdentitiesByemail(orgCode,email)
  }

  override def getUserIdentitiesById(orgCode:String,email: String, id: String): Future[Option[UserIdentities]] = {
    UserIdentitiesRepository.getUserIdentitiesById(orgCode,email,id)
  }

  override def getUserIdentitiesByorgCode(orgCode:String):Future[Seq[UserIdentities]]={
    UserIdentitiesRepository.getUserIdentitiesByorgCode(orgCode)
  }
}
