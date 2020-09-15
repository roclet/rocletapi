package controllers.util

import domain.util.{Credential, Token}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.UserService
import services.util.TokenService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/09/26.
  */
class TokenController extends Controller {
  def createNewToken = Action.async(parse.json) {
    request => {
      val input = request.body
      val credential = Json.fromJson[Credential](input).get
      val token = TokenService.apply().createNewToken(credential)
      token.map(result =>
        Ok(Json.toJson(result))
      )
    }
  }

  def revoketoken(token: String) = Action.async {
    request =>
      TokenService.apply.revokeToken(token) map (result =>
        Ok(Json.toJson("ROVOKED")))
  }

  def isTokenValid(token: String, password: String) = Action.async {
    request =>
      TokenService.apply.isTokenValid(token, password) map (result =>
        Ok(Json.toJson(result)))
  }

  def hasTokenExpired(token: String) = Action.async {
    request =>
      TokenService.apply.hasTokenExpired(token) map (result =>
        Ok(Json.toJson(result)))
  }
}
