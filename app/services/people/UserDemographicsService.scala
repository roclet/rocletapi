package services.people

import com.websudos.phantom.dsl._
import domain.people.UserDemographics
import services.people.Impl.UserDemographicsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/08.
  */
trait UserDemographicsService {
  def save(userdemographics:UserDemographics):Future[ResultSet]
  def getUserDemographicsByemail(orgCode:String,email:String):Future[Option[UserDemographics]]
  def getUserDemographicByOrgCode(orgCode:String):Future[Seq[UserDemographics]]
}
object UserDemographicsService{
  def apply(): UserDemographicsService = new UserDemographicsServiceImpl
}
