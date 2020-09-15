package controllers.util

import conf.security.TokenCheck
import domain.util.ExternalRoles
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.ExternalRolesService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/03/04.
  */
class ExternalRolesController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[ExternalRoles](input).get
      println(entity)
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- ExternalRolesService.apply.create(entity) if auth.status == "VALID"
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
        results <- ExternalRolesService.apply.getById(id) if auth.status == "VALID"
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
        results <- ExternalRolesService.apply.getAll if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
