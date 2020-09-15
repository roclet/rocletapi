package controllers.people

import domain.people.UserOtp
import domain.people.UserOptEvent
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserOtpService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/26.
  */
class UserOtpController extends Controller{
  def createUserOtp = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[UserOtp](input).get
      val results = UserOtpService.apply.saveUserOtp(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getUserOtps(orgCode:String,email:String) = Action.async {
    request =>
      UserOtpService.apply.getUserOtps(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUserOtpByOtpId(orgCode:String,email:String,userOtpId:String) = Action.async {
    request =>
      UserOtpService.apply.getUserOtpByOtpId(orgCode,email,userOtpId) map (result =>
        Ok(Json.toJson(result)))
  }
  def getCurrentUserOtp(orgCode:String,email:String) = Action.async {
    request =>
      UserOtpService.apply.getCurrentUserOtp(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def saveOtpEvent=Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[UserOptEvent](input).get
      val results = UserOtpService.apply.saveOtpEvent(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getOtpEventById(userOtpId:String,id:String) = Action.async {
    request =>
      UserOtpService.apply.getOtpEventById(userOtpId,id) map (result =>
        Ok(Json.toJson(result)))
  }
  def getOptEvents(userOtpId:String) = Action.async {
    request =>
      UserOtpService.apply.getOptEvents(userOtpId) map (result =>
        Ok(Json.toJson(result)))
  }
}
