package controllers.people

import domain.people.User
import domain.people.UserRole
import domain.people.UserStatusChange
import domain.util.Credential

import scala.concurrent.Future
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserService

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by hashcode on 2016/09/11.
  */
class UserController extends Controller {

  def createNewUser = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[User](input).get
      val results = UserService.apply.createUser(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def saveUserStatusChange()=Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[UserStatusChange](input).get
      val results = UserService.apply.saveUserStatusChange(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getOrganisationUsers(orgCode:String)=Action.async{

      request=>
        val users:Future[Seq[User]] = UserService.apply.getOrganisationUsers(orgCode)
        users.map(result =>
          Ok(Json.toJson(result)))
  }
  def saveUserRole=Action.async(parse.json){
    request=>
      val input = request.body
      val entity = Json.fromJson[UserRole](input).get
      val results = UserService.apply.saveUserRole(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getUserStatusChangeorgCode(orgCode:String)=Action.async{
    request=>
      val users = UserService.apply.getUserStatusChangeorgCode(orgCode)
      users.map(result =>
        Ok(Json.toJson(result)))
  }
  def getAllUserStatusChange(orgCode:String,email:String)=Action.async{
    request=>
      val users = UserService.apply.getAllUserStatusChange(orgCode,email)
      users.map(result =>
        Ok(Json.toJson(result)))
  }
  def getUser(email:String) =Action.async{

    request=>
      val users:Future[Seq[User]] = UserService.apply.getUser(email)
      users.map(result =>
        Ok(Json.toJson(result)))
  }

  def updateUser =Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[User](input).get
      val results = UserService.apply.updateUser(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getUserByEmail(orgCode:String, email:String)=Action.async{
    request=>
      val users:Future[Option[User]] = UserService.apply.getUserByEmail(orgCode,email)
      users.map(result =>
        Ok(Json.toJson(result)))
  }

  def getUserRolesByEmail(orgCode:String, email:String)=Action.async{
    request=>
      val users = UserService.apply.getUserRoles(orgCode,email)
      users.map(result =>
        Ok(Json.toJson(result)))
  }

  def getUserRolesByorgCode(orgCode:String)=Action.async{
    request=>
      val users = UserService.apply.getUserRolesByorgCode(orgCode)
      users.map(result =>
        Ok(Json.toJson(result)))
  }

  def getCredentialValidation = Action.async(parse.json){
    request =>
      val input = request.body
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode")
      val entity = Json.fromJson[Credential](input).get
      val results = UserService.apply.validateUser(orgCode.getOrElse(""),entity)
      results.map(result =>
        Ok(Json.toJson(result)))
  }

  def getUpdatePasswordStatus = Action.async(parse.json){
    request =>
      val input = request.body
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode")
      val entity = Json.fromJson[Credential](input).get
      val results = UserService.apply.updateCredential(orgCode.getOrElse(""),entity)

      results.map(result =>
        Ok(Json.toJson(result)))
  }
}
