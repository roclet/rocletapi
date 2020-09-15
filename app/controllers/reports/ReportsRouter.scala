package controllers.reports

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by kuminga on 2016/09/24.
  */
class ReportsRouter @Inject()
(balanceSheet: BalanceSheetController)
(incomeStatement: IncomeStatementController)
(financialRatioController: FinancialRatioController)
(weightingController: WeightingController)
(scoreCardController: ScoreCardController)
(financial: FinancialController) extends SimpleRouter {
  override def routes: Routes = {

    case GET(p"/balancesheet/$orgCode") =>
      balanceSheet.getStatementsByType(orgCode)
    case POST(p"/balancesheet/category/$orgCode/$category") =>
      balanceSheet.getStatementsByCategory(orgCode, category)
    case GET(p"/balancesheet/year/$orgCode/$category/${int(year)}") =>
      balanceSheet.getStatementsByYear(orgCode, category, year)
    case GET(p"/balancesheet/month/$orgCode/$category/${int(year)}/${int(month)}") =>
      balanceSheet.getStatementsByMonth(orgCode, category, year, month)

    case GET(p"/incomestatement/$orgCode") =>
      incomeStatement.getStatementsByType(orgCode)
    case POST(p"/incomestatement/category/$orgCode/$category") =>
      incomeStatement.getStatementsByCategory(orgCode, category)
    case GET(p"/incomestatement/year/$orgCode/$category/${int(year)}") =>
      incomeStatement.getStatementsByYear(orgCode, category, year)
    case GET(p"/incomestatement/month/$orgCode/$category/${int(year)}/${int(month)}") =>
      incomeStatement.getStatementsByMonth(orgCode, category, year, month)

    case GET(p"/statements/all/$orgCode") =>
      financial.getStatements(orgCode)

      //Scorecard
    case GET(p"/scorecard/$orgCode/$period/${int(year)}/${int(month)}") =>
      scoreCardController.getScoreCard(orgCode,period,year,month)

      // Weightings
    case POST(p"/weightings/create")=>
    weightingController.save
    case GET(p"/weightings/get/$orgCode/$kpa") =>
      weightingController.getWeighting(orgCode,kpa)
    case GET(p"/weightings/all/$orgcode") =>
      weightingController.getWeightings(orgcode)

    // Ratios
    case POST(p"/ratios/create") =>
      financialRatioController.save
    case GET(p"/ratios/kpa/$orgCode/$kpa") =>
      financialRatioController.getFinancialRatioKpa(orgCode,kpa)
    case GET(p"/ratios/kpi/$orgcode/$kpa/$kpi") =>
      financialRatioController.getFinancialRatioKpi(orgcode,kpa,kpi)
    case GET(p"/ratios/all/$orgcode") =>
      financialRatioController.getFinancialRatios(orgcode)
    case GET(p"/ratios/kpas/$orgcode") =>
      financialRatioController.getKPA(orgcode)

  }
}
