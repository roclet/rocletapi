package services.people.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.people.{UserSession, UserSessionEvent}
import repositories.people.{UserSessionEventRepository, UserSessionsRepository}
import services.Service
import services.people.UserTokenService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class UserTokenServiceImpl extends UserTokenService with Service {
  override def saveUserSessions(usersession: UserSession): Future[ResultSet] = {
    UserSessionsRepository.save(usersession)
  }

  override def getCurrentUserSession(orgCode:String,email: String): Future[UserSession] = {
    UserSessionsRepository.getUserSessionsByemail(orgCode,email) map(session=>
      session.head)
  }

  override def getUserSessions(orgCode:String,email: String): Future[Seq[UserSession]] = {
    UserSessionsRepository.getUserSessionsByemail(orgCode,email)
  }

  override def getUserSessionsById(orgCode:String,email: String, userSessionId: String): Future[Option[UserSession]] = {
    UserSessionsRepository.getUserSessionsById(orgCode,email, userSessionId)
  }

  override def saveTokenEvent(tokenevents: UserSessionEvent): Future[ResultSet] = {
    UserSessionEventRepository.save(tokenevents)
  }

  override def getTokenEvents(tokenId: String): Future[Seq[UserSessionEvent]] = {
    UserSessionEventRepository.getUserSessionEventBysessionId(tokenId)
  }

  override def getTokenEvent(tokenId: String, id: String): Future[Option[UserSessionEvent]] = {
    UserSessionEventRepository.getUserSessionEventById(tokenId, id)
  }

  override def getCurrentTokenEvent(tokenId: String): Future[UserSessionEvent] = {
    UserSessionEventRepository.getUserSessionEventBysessionId(tokenId) map (tokens => {
      tokens.head
    })
  }


}
