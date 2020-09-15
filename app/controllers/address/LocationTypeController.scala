package controllers.address

import conf.security.TokenCheck
import domain.address.LocationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.address.LocationTypeService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/18.
  */
class LocationTypeController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[LocationType](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- LocationTypeService.apply.save(entity) if (auth.status == "VALID")
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
        results <- LocationTypeService.apply.findById(id) if auth.status == "VALID"
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
        results <- LocationTypeService.apply.findAll if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

}
