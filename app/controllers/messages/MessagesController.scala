package controllers.messages

import domain.messages.Messages
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.messages.MessagesService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by hashcode on 2016/09/18.
  */
class MessagesController extends Controller{
   def saveOrupedate=Action.async(parse.json){
     request =>
       val input = request.body
       val entity = Json.fromJson[Messages](input).get
       val results = MessagesService.apply.save(entity)
       results.map(result=>
          Ok(Json.toJson(entity))
       )
   }
  def getAll(OrgCode:String)=Action.async{
      request=>
        MessagesService.apply.getMessageByorgCode(OrgCode) map (result =>
    Ok(Json.toJson(result)))
  }
  def getMessageDate(OrgCode:String,date:DateTime)=Action.async{
    request=>
      MessagesService.apply.getMessageDate(OrgCode,date) map (result =>
        Ok(Json.toJson(result)))
  }
  def getMessageById(orgCode:String,date:DateTime,id:String)=Action.async{
    request=>
      MessagesService.apply.getMessageById(orgCode,date,id) map (result =>
        Ok(Json.toJson(result)))
  }
}
