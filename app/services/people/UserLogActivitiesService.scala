package services.people

import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import domain.people.UserLogActivities
import play.api.cache.redis.Builders._
import repositories.people.UserLogActivitiesRepository._
import services.people.Impl.UserLogActivitiesServiceImpl

/**
  * Created by hashcode on 2016/09/08.
  */
trait UserLogActivitiesService {
  def save(userLogactivities: UserLogActivities): Future[ResultSet]

  def getUserLogActivitiesByorgCode(orgCode:String):Future[Seq[UserLogActivities]]

  def getUserLogActivitiesByemail(orgCode:String,email: String): Future[Seq[UserLogActivities]]

  def getUserLogActivitiesBySessionId(orgCode:String,email: String, sessionId: String): Future[Seq[UserLogActivities]]

  def getUserLogActivitiesById(orgCode:String,email: String, sessionId: String, id: String): Future[Option[UserLogActivities]]
}

object UserLogActivitiesService{
  def apply(): UserLogActivitiesService = new UserLogActivitiesServiceImpl
}
