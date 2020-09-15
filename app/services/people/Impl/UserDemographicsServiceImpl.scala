package services.people.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.people.UserDemographics
import repositories.people.UserDemographicsRepository
import services.Service
import services.people.UserDemographicsService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class UserDemographicsServiceImpl extends UserDemographicsService with Service{
  override def save(userdemographics: UserDemographics): Future[ResultSet] = {
    UserDemographicsRepository.save(userdemographics)
  }
  override def getUserDemographicByOrgCode(orgCode:String):Future[Seq[UserDemographics]]={
    UserDemographicsRepository.getUserDemographicByOrgCode(orgCode)
  }
  override def getUserDemographicsByemail(orgCode:String,email:String): Future[Option[UserDemographics]] = {
    UserDemographicsRepository.getUserDemographicsByemail(orgCode,email)
  }
}
