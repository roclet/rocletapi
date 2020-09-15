package services.people.Impl

import com.github.t3hnar.bcrypt._
import com.websudos.phantom.dsl
import com.websudos.phantom.dsl.ResultSet
import conf.security.{AuthUtil, RolesID}
import domain.organisation.Organisation
import domain.people.{Login, User, UserRole, UserStatusChange}
import domain.util.Credential
import repositories.people._
import services.Service
import services.mail.MailPasswordService
import services.organisation.OrganisationServices
import services.people.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
  * Created by hashcode on 2016/09/04.
  */
class UserServiceImpl extends UserService with Service {

  override def createUser(user: User): Future[dsl.ResultSet] = {
    val role = UserRole(user.orgCode,user.email, RolesID.READER)
    for {
      available <- checkUserAvailability(user: User)
      result <- UserRepository.save(user.copy(password = AuthUtil.encode(user.password))) if !available
      saveRole <- UserRoleRepository.save(role) if !available
      mailSent <- MailPasswordService.apply.sendMail(user: User) if !available
    } yield result
  }

  override def checkUserAvailability(user: User): Future[Boolean] = {
    UserRepository.getUserByEmail(user.orgCode,user.email) map {
      case Some(x) => true
      case _ => false
    }
  }

  override def getUserRoles(orgCode:String, email:String): Future[Seq[UserRole]] = {
    UserRoleRepository.getByUseremail(orgCode,email)
  }


  override def getUserByEmail(orgCode:String,email:String): Future[Option[User]] = {
    UserRepository.getUserByEmail(orgCode,email)
  }

  override def updateUser(user: User): Future[ResultSet] = {
    UserRepository.save(user)
  }

  override def saveUserRole(userrole: UserRole): Future[ResultSet] = {
    UserRoleRepository.save(userrole)
  }

  override def saveUserStatusChange(userstatuschange:UserStatusChange):Future[ResultSet]={
    UserStatusChangeRepository.save(userstatuschange)
  }
  override def getUserStatusChangeorgCode(orgCode:String):Future[Seq[UserStatusChange]]={
    UserStatusChangeRepository.getUserStatusChangeorgCode(orgCode)
  }
  override def getAllUserStatusChange(orgCode:String,email:String):Future[Seq[UserStatusChange]]={
    UserStatusChangeRepository.getAllUserStatusChange(orgCode,email)
  }
  override def getOrganisationUsers(orgCode: String): Future[Seq[User]] = {
    UserRepository.getOrgUsers(orgCode)
  }

  override def getUser(email: String): Future[Seq[User]] = {

    UserEmailRepository.getUser(email)

  }
  override def getUserRolesByorgCode(orgCode:String): Future[Seq[UserRole]]={
    UserRoleRepository.getUserRoleByorgCode(orgCode)
  }

  override def updateCredential(orgCode:String, entity: Credential): Future[Boolean] = {
    val user = getUserByEmail(orgCode, entity.email)
    user map (saveUser => {
      val updated = saveUser.get.copy(password = AuthUtil.encode(entity.password))
      val result =  UserRepository.save(updated)
      result.isCompleted
    })
  }

  override def validateUser(orgCode:String,entity: Credential): Future[Boolean] = {
   getUserByEmail(orgCode, entity.email) map( savedUser => entity.password.isBcrypted(savedUser.get.password))

  }

  override def getOrganisationOwner(orgcode: String): Future[User] = {
    for{
      users <- UserService.apply().getOrganisationUsers(orgcode)
      usersRoles <-  getUserRolesByorgCode(orgcode)
    } yield{
      val ownerEmail = usersRoles.filter(role=> role.roleId==RolesID.ORG_ADMIN).head
      users.filter( user=> user.email==ownerEmail.email).head
    }
  }
}
