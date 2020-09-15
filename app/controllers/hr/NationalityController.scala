package controllers.hr

import conf.security.TokenCheck
import domain.hr.Nationality
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.hr.NationalityService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/04/07.
  */
class NationalityController extends Controller {

  def createOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Nationality](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- NationalityService.apply.save(entity) if (auth.status == "VALID")
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

  def getNationalities = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- NationalityService.apply.getNationalities if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            e.printStackTrace()
            Unauthorized
          }
        }
  }

  def getNationality(code: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- NationalityService.apply.getNationality(code) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
