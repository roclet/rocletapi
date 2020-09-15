package services.people

import com.websudos.phantom.dsl._
import domain.people.UserIdentities
import repositories.people.UserIdentitiesRepository._
import services.people.Impl.UserIdentitiesServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/08.
  */
trait UserIdentitiesService {
  def save(useridentities:UserIdentities):Future[ResultSet]
  def getUserIdentitiesByorgCode(orgCode:String):Future[Seq[UserIdentities]]
  def getUserIdentitiesByemail(orgCode:String,email:String):Future[Seq[UserIdentities]]
  def getUserIdentitiesById(orgCode:String,email:String,id:String):Future[Option[UserIdentities]]
}
object UserIdentitiesService{
  def apply(): UserIdentitiesService = new UserIdentitiesServiceImpl
}
