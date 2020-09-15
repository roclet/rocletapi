package controllers.syslogs

import domain.syslogs.SystemLogEvents
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.syslogs.SystemLogEventsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/18.
  */
class SyslogsController extends Controller{
  def savelogs = Action.async(parse.json){
    request=>
      val input = request.body
      val entity = Json.fromJson[SystemLogEvents](input).get
      val results = SystemLogEventsService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getOrganisationLogs(orgCode:String)=Action.async{
     request=>
       SystemLogEventsService.apply.getOrganisationLogs(orgCode) map(
         result =>
           Ok(Json.toJson(result))
         )
  }
  def getOrganisationLogEvent(orgCode:String,id:String)=Action.async{
    request=>
      SystemLogEventsService.apply.getOrganisationLogEvent(orgCode,id) map(
        result =>
          Ok(Json.toJson(result))
        )
  }

}
