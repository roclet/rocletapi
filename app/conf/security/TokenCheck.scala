package conf.security

import java.io.File

import play.api.libs.json.JsValue
import play.api.mvc.{AnyContent, MultipartFormData, Request}
import services.util.TokenService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/06.
  */
object TokenCheck {

  def getTokenForUpload(request: Request[MultipartFormData[File]]) = {
    getTokenValue(request.headers.get("Authorization"))
  }


  def getTokenfromParam(request: Request[AnyContent]) = {
    getTokenValue(request.headers.get("Authorization"))
  }



  def getToken(request: Request[JsValue]) = {
    getTokenValue(request.headers.get("Authorization"))
  }


  def getTokenValue(token: Option[String]): Future[LogInStatus] = {
    val tokenValue = token match {
      case Some(t) => t
      case None => ""
    }
    check(tokenValue) map (tkn => {
      if (tkn) LogInStatus("VALID")
      else LogInStatus("INVALID")
    })
  }

  private def check(token: String) = {
    val code = TokenService.apply().getEmail(token)
    TokenService.apply.isTokenValid(token, code)
  }


}
