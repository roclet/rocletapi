package controllers.financials

import conf.security.TokenCheck
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.{FinancialApprovedStatementService, FinancialStatementRecordsService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/10/15.
  */
class FinancialApprovedStatementController extends Controller {


  def getOrganisationStatements(orgCode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialApprovedStatementService.apply.getOrganisationStatements(orgCode)// if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getOrganisationStatementsByCategory(orgCode: String, statementType: String, category: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialApprovedStatementService
          .apply.
          getOrganisationStatementsByCategory(orgCode, statementType, category) //if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getOrganisationStatementsByCategory(orgCode: String, statementType: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FinancialApprovedStatementService
          .apply.
          getOrganisationStatementsType(orgCode,statementType) //if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
