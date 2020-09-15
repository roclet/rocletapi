package controllers.people

import domain.people.UserLogActivities
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserLogActivitiesService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/25.
  */
class UserLogActivitiesController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserLogActivities](input).get
      val results = UserLogActivitiesService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def ByorgCode(orgCode:String)= Action.async{
    request =>
      UserLogActivitiesService.apply.getUserLogActivitiesByorgCode(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }
  def Byemail(orgCode:String,email: String)= Action.async{
    request =>
      UserLogActivitiesService.apply.getUserLogActivitiesByemail(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def BySessionId(orgCode:String,email: String,sessionId:String)= Action.async{
    request =>
      UserLogActivitiesService.apply.getUserLogActivitiesBySessionId(orgCode,email,sessionId) map (result =>
        Ok(Json.toJson(result)))
  }
  def ById(orgCode:String,email: String,sessionId:String,id: String)= Action.async{
    request =>
      UserLogActivitiesService.apply.getUserLogActivitiesById(orgCode,email,sessionId,id) map (result =>
        Ok(Json.toJson(result)))
  }

}
