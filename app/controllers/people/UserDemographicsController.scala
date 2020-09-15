package controllers.people

import domain.people.UserDemographics
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserDemographicsService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/25.
  */
class UserDemographicsController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserDemographics](input).get
      val results = UserDemographicsService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def Byemail(orgCode:String,email:String)= Action.async{
    request =>
      UserDemographicsService.apply.getUserDemographicsByemail(orgCode,email) map (result =>
        Ok(Json.toJson(result)))
  }
  def ByOrgCode(orgCode:String)= Action.async{
     request =>
       UserDemographicsService.apply.getUserDemographicByOrgCode(orgCode) map(result =>
         Ok(Json.toJson(result))
         )
  }


}
