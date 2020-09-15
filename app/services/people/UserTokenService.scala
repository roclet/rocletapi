package services.people

import com.websudos.phantom.dsl._
import domain.people.{UserSession, UserSessionEvent}
import services.people.Impl.UserTokenServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/08.
  */
trait UserTokenService {
  def saveUserSessions(usersession: UserSession): Future[ResultSet]

  def getUserSessions(orgCode:String,email: String): Future[Seq[UserSession]]

  def getCurrentUserSession(orgCode:String,email: String): Future[UserSession]

  def getUserSessionsById(orgCode:String,email: String, userSessionId: String): Future[Option[UserSession]]

  def saveTokenEvent(tokenevents: UserSessionEvent): Future[ResultSet]

  def getTokenEvents(tokenId: String): Future[Seq[UserSessionEvent]]

  def getTokenEvent(tokenId: String, id: String): Future[Option[UserSessionEvent]]

  def getCurrentTokenEvent(tokenId: String): Future[UserSessionEvent]


}

object UserTokenService {
  def apply(): UserTokenService = new UserTokenServiceImpl()
}
