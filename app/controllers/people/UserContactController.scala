package controllers.people

import domain.people.UserContact
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserContactService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/25.
  */
class UserContactController extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserContact](input).get
      val results = UserContactService.apply.createOrupdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def ByorgCode(orgCode:String)=Action.async {
    request =>
      UserContactService.apply.getAllUserContactByorgCode(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }
  def Byemail(orgCode:String,email:String)=Action.async{
     request =>
       UserContactService.apply.getAllUserContactByemail(orgCode,email) map( result =>
         Ok(Json.toJson(result))
       )
  }
  def Byid(orgCode:String,email:String,id:String)=Action.async{
      request =>
        UserContactService.apply.getAllUserContactByid(orgCode,email,id) map( result =>
          Ok(Json.toJson(result))

          )
  }

}
