package controllers.reports

import conf.security.TokenCheck
import domain.reports.Weighting
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.reports.WeightingService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/04/12.
  */
class WeightingController extends Controller {

  def save = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Weighting](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- WeightingService.apply.save(entity) if (auth.status == "VALID")
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

  def getWeighting(orgcode: String, kpa: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- WeightingService.apply.getWeighting(orgcode,kpa) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            e.printStackTrace()
            Unauthorized
          }
        }
  }

  def getWeightings(orgcode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- WeightingService.apply.getWeitings(orgcode) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
