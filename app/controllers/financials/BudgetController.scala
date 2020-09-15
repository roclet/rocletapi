package controllers.financials

import domain.financials.Budget
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.BudgetService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/11/28.
  */
class BudgetController extends Controller {

  def saveOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Budget](input).get
      val results = BudgetService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getOrganisationBudgets(orgCode: String) = Action.async {
    request =>
      BudgetService.apply.getOrganisationBudgets(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }

  def getOrganisationBudgetByYear(orgCode: String, year: Int) = Action.async {
    request =>
      BudgetService.apply.getOrganisationBudgetByYear(orgCode, year) map (result =>
        Ok(Json.toJson(result)))
  }

  def getBudgetSetItems(orgCode: String, year: Int) = Action.async {
    request =>
      BudgetService.apply.getOrganisationBudgetByYear(orgCode, year) map (budgets => {
        val results = budgets.filter(budget => budget.amount != 0)
        Ok(Json.toJson(results))
      })

  }


  def getBudgetSetNotItems(orgCode: String, year: Int) = Action.async {
    request =>
      BudgetService.apply.getOrganisationBudgetByYear(orgCode, year) map (budgets => {
        val results = budgets.filter(budget => budget.amount == 0)
        Ok(Json.toJson(results))
      }
        )
  }

  def getBudgetItem(orgCode: String, year: Int, month: Int, item: String) = Action.async {
    request =>
      BudgetService.apply.getOrganisationBudgetItemByMonth(orgCode, year, month, item) map (budgets => {
        val results = budgets.filter(budget => budget.amount == 0)
        Ok(Json.toJson(results))
      }
        )
  }


  def getSetItemsBudgetByMonth(orgCode: String, year: Int, month: Int) = Action.async {
    request =>
      BudgetService.apply.getItemsBudgetByMonth(orgCode, year, month: Int) map (budgets => {
        val results = budgets.filter(budget => budget.amount != 0)
        Ok(Json.toJson(results))
      })
  }

  def getItemsNotBudgetByMonth(orgCode: String, year: Int, month: Int) = Action.async {
    request =>
      BudgetService.apply.getItemsBudgetByMonth(orgCode, year, month: Int) map (budgets => {
        val results = budgets.filter(budget => budget.amount == 0)
        Ok(Json.toJson(results))
      })
  }

}
