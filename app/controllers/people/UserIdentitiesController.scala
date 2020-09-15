package controllers.people

import domain.people.UserIdentities
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserIdentitiesService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/25.
  */
class UserIdentitiesController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      println(input)
      val entity = Json.fromJson[UserIdentities](input).get
      val results = UserIdentitiesService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def ByorgCode(orgCode:String)= Action.async{
    request =>
      UserIdentitiesService.apply.getUserIdentitiesByorgCode(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }
  def Byemail(orgCode:String,email:String)= Action.async{
    request =>
      UserIdentitiesService.apply.getUserIdentitiesByemail(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def ById(orgCode:String,email:String,id:String)= Action.async{
    request =>
      UserIdentitiesService.apply.getUserIdentitiesById(orgCode,email,id) map (result =>
        Ok(Json.toJson(result)))
  }

}
