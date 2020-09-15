package controllers.people

import domain.people.UserSession
import domain.people.UserSessionEvent
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserTokenService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/26.
  */
class UserTokenController extends Controller{
  def saveUserSessions = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[UserSession](input).get
      val results = UserTokenService.apply.saveUserSessions(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getUserSessions(orgCode:String,email: String)=Action.async{
    request =>
      UserTokenService.apply.getUserSessions(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def getCurrentUserSession(orgCode:String,email: String)=Action.async{
    request =>
      UserTokenService.apply.getCurrentUserSession(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUserSessionsById(orgCode:String,email: String, userSessionId: String)=Action.async{
    request =>
      UserTokenService.apply.getUserSessionsById(orgCode,email,userSessionId) map (result =>
        Ok(Json.toJson(result)))
  }
  def saveTokenEvent = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[UserSessionEvent](input).get
      val results = UserTokenService.apply.saveTokenEvent(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getTokenEvents(tokenId: String)=Action.async{
    request =>
      UserTokenService.apply.getTokenEvents(tokenId) map (result =>
        Ok(Json.toJson(result)))
  }
  def getTokenEvent(tokenId: String,id:String)=Action.async{
    request =>
      UserTokenService.apply.getTokenEvent(tokenId,id) map (result =>
        Ok(Json.toJson(result)))
  }
}
