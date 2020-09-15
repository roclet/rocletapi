package controllers.reports

import conf.security.TokenCheck
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.reports.ScoreCardService
import scala.concurrent.ExecutionContext.Implicits.global

class ScoreCardController extends Controller{

  def getScoreCard(orgcode:String, period: String, year: Int, month: Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- ScoreCardService
          .apply.getScoreCard(orgcode,period,year,month) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
