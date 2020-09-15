package controllers.address

import conf.security.TokenCheck
import domain.address.AddressType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.address.AddressTypeService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/18.
  */
class AddressTypeController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AddressType](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- AddressTypeService.apply.save(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
  def getById(id: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- AddressTypeService.apply.findById(id) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
  def getAll = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- AddressTypeService.apply.findAll if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
