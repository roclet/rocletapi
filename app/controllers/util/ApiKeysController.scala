package controllers.util

import conf.security.TokenCheck
import conf.util.Util
import domain.util.ApiKeys
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.ApiKeysService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/10/10.
  */
class ApiKeysController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[ApiKeys](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- ApiKeysService.apply.saveOrUpdate(entity)
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
        results <- ApiKeysService.apply.get(id)
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCompanyKey(name: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
      } yield auth
      response.map(ans => Ok(Json.toJson(Util.codeGen(name))))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getAll = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- ApiKeysService.apply.getAll if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
