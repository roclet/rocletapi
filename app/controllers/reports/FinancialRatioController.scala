package controllers.reports

import conf.security.TokenCheck
import domain.reports.FinancialRatio
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.reports.{FinancialRatioService, KpaService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/04/12.
  */
class FinancialRatioController extends Controller {

  def save = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FinancialRatio](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- FinancialRatioService.apply.save(entity) if (auth.status == "VALID")
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

  def getFinancialRatioKpi(orgcode:String, kpa:String, kpi:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialRatioService.apply.getFinancialRatioKpi(orgcode,kpa,kpi) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            e.printStackTrace()
            Unauthorized
          }
        }
  }

  def getFinancialRatioKpa(orgcode: String, kpa:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialRatioService.apply.getFinancialRatioKpa(orgcode, kpa) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getFinancialRatios(orgcode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialRatioService.apply.getFinancialRatios(orgcode) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getKPA(orgcode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- KpaService.apply.getKPAs(orgcode)
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
