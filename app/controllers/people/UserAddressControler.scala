package controllers.people

import domain.people.UserAddress
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserAddressService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/24.
  */
class UserAddressControler extends Controller{
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[UserAddress](input).get
      val results = UserAddressService.apply.createOrupdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getByOrg(orgCode:String) = Action.async{
    request =>
      UserAddressService.apply.findUserAddressByOrg(orgCode) map (
        result =>
          Ok(Json.toJson(result))
        )
  }
  def getByEmail(orgCode:String,email:String) = Action.async{
     request =>
       UserAddressService.apply.findUserAddressByEmail(orgCode,email) map(
         result =>
           Ok(Json.toJson(result))
         )
  }
  def getById(orgCode:String,email:String,id:String) = Action.async{
     requet =>
       UserAddressService.apply.findUserAddressById(orgCode,email,id) map(
          result =>
            Ok(Json.toJson(result))
         )
  }

}
