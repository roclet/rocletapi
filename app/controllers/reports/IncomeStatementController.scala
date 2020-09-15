package controllers.reports

import conf.security.TokenCheck
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.FinancialReportsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/08.
  */
class IncomeStatementController  extends Controller{

  val INCOMESTATEMENT = "INCOMESTATEMENT"

  def getStatementsByType(orgCode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialReportsService
          .apply.
          getStatementsByType(orgCode, INCOMESTATEMENT) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getStatementsByCategory(orgCode: String, category: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialReportsService
          .apply.
          getStatementsByCategory(orgCode, INCOMESTATEMENT, category) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }


  def getStatementsByYear(orgCode: String,  category: String, year:Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialReportsService
          .apply.
          getStatementsByYear(orgCode, INCOMESTATEMENT, category, year) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getStatementsByMonth(orgCode: String,  category: String, year:Int, month:Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialReportsService
          .apply.
          getStatementsByMonth(orgCode, INCOMESTATEMENT, category, year, month) //if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
