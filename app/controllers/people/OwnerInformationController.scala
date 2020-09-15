package controllers.people

import conf.security.TokenCheck
import domain.people.OwnerInformation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.people.OwnerInformationService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/04/07.
  */
class OwnerInformationController extends Controller {

  def saveOrupdate = Action.async(parse.json) {
    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode").getOrElse("")
      val input = request.body
      val entity = Json.fromJson[OwnerInformation](input).get
      val results = OwnerInformationService.apply.save(orgCode, entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getOwnerInformation = Action.async {
    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email").getOrElse("")
      val orgCode = request.headers.get("orgCode").getOrElse("")
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OwnerInformationService.apply.getOwnerInformation(orgCode) //if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            println(e.getStackTrace)
            Unauthorized
          }
        }
  }
}
