package services.people.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.people.UserLogActivities
import play.api.cache.redis.Builders.Future
import repositories.people.UserLogActivitiesRepository
import services.Service
import services.people.UserLogActivitiesService

/**
  * Created by hashcode on 2016/09/09.
  */
class UserLogActivitiesServiceImpl extends UserLogActivitiesService with Service {
  override def save(userLogactivities: UserLogActivities): Future[ResultSet] = {
    UserLogActivitiesRepository.save(userLogactivities)
  }

  override def getUserLogActivitiesByemail(orgCode:String,email: String): Future[Seq[UserLogActivities]] = {
    UserLogActivitiesRepository.getUserLogActivitiesByemail(orgCode,email)
  }

  override def getUserLogActivitiesBySessionId(orgCode:String,email: String, sessionId: String): Future[Seq[UserLogActivities]] = {
    UserLogActivitiesRepository.getUserLogActivitiesBySessionId(orgCode,email,sessionId)
  }

  override def getUserLogActivitiesById(orgCode:String,email: String, sessionId: String, id: String): Future[Option[UserLogActivities]] = {
    UserLogActivitiesRepository.getUserLogActivitiesById(orgCode,email,sessionId,id)
  }
  override  def getUserLogActivitiesByorgCode(orgCode:String):Future[Seq[UserLogActivities]]={
    UserLogActivitiesRepository.getUserLogActivitiesByorgCode(orgCode)
  }
}
