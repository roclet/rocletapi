package services.people

import com.websudos.phantom.dsl
import com.websudos.phantom.dsl._
import domain.people._
import domain.util.Credential
import services.Service
import services.people.Impl.UserServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/08/28.
  */
trait UserService {
  def updateCredential(orgCode:String, entity: Credential):Future[Boolean]

  def validateUser(orgCode:String, entity: Credential):Future[Boolean]

  def getOrganisationUsers(orgCode: String):Future[Seq[User]]

  def getOrganisationOwner(orgcode:String):Future[User]

  def getUser(email: String):Future[Seq[User]]

  def createUser(user: User): Future[dsl.ResultSet]

  def updateUser(user: User): Future[dsl.ResultSet]
  def getUserByEmail(orgCode:String,email:String): Future[Option[User]]
  def checkUserAvailability(user: User): Future[Boolean]
  // User Role
  def saveUserRole(userrole: UserRole): Future[ResultSet]
  def getUserRoles(orgCode:String, email:String): Future[Seq[UserRole]]
  def getUserRolesByorgCode(orgCode:String): Future[Seq[UserRole]]

  //Change Status
  def saveUserStatusChange(userstatuschange:UserStatusChange):Future[ResultSet]
  def getUserStatusChangeorgCode(orgCode:String):Future[Seq[UserStatusChange]]
  def getAllUserStatusChange(orgCode:String,email:String):Future[Seq[UserStatusChange]]

}

object UserService extends Service {
  def apply(): UserService = new UserServiceImpl
}

