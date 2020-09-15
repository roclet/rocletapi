package controllers.reports

import conf.security.TokenCheck
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.FinancialReportsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/18.
  */
class FinancialController extends Controller{

  def getStatements(orgCode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialReportsService
          .apply.
          getStatements(orgCode) if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }




}
