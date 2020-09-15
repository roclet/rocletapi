package services.reports.Impl

import domain.reports.{FinancialRatio, KpiIndicator, Weighting}
import services.reports.{FinancialRatioService, KpaService, WeightingService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/13.
  */
class KpaServiceImpl extends KpaService{

  override def indicator(target: String, uom: String, importance: String, ftype: String): Map[String, String] = {
    Map("target" -> target, "uom" -> uom, "importance" -> importance, "type" -> ftype)
  }

  override def  profitabilityKpi(kpi:Map[String,Map[String,String]]):Map[String,Map[String,String]] = kpi

  override def  profitabilityKpa(weighting:String, kpi:Map[String,Map[String,String]]):KpiIndicator = KpiIndicator(weighting,kpi)

  override def getKPAs(orgcode: String): Future[Map[String, KpiIndicator]] = {

    for{
      //Profitability
      profitabilityWeightings <- WeightingService.apply.getWeighting(orgcode,"Profitability")
      grossProfit <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Profitability","Gross Profit")
      operatingProfit <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Profitability","Operating Profit")
      netProfit <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Profitability","Net Profit")

      // Returns
      returnsWeightings <- WeightingService.apply.getWeighting(orgcode,"Returns")
      returnsOnAssets <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Returns","Return on Assets")
      returnOnEquity <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Returns","Return on Equity")
      returnOnCapitalEmployed <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Returns","Return on Capital Employed")



      // Gearing
      gearingWeightings <- WeightingService.apply.getWeighting(orgcode,"Gearing")
      debtToEquity <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Gearing","Debt-to-Equity")
      debtToAssets <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Gearing","Debt-to-Assets")
      equityToAssets <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Gearing","Equity-to-Assets")


      //Liquidity
      liquidityWeightings <- WeightingService.apply.getWeighting(orgcode,"Liquidity")
      quickRatio <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Liquidity","Quick Ratio")
      currentRatio <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Liquidity","Current Ratio")
      cashRatio <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Liquidity","Cash Ratio")



      //Capital
      capitalWeightings <- WeightingService.apply.getWeighting(orgcode,"Capital")
      inventoryTurns <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Capital","Inventory Turns")
      workingCapital <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Capital","Working Capital")
      inventoryDays <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Capital","Inventory Days")


      // Cash Flow
      cashFlowWeightings <- WeightingService.apply.getWeighting(orgcode,"Cash Flow")
      cashFlowMargin <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Cash Flow","Cash Flow Margin")
      netCashFlow <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Cash Flow","Net Cash Flow")
      freeCashFlow <-FinancialRatioService.apply.getFinancialRatioKpi(orgcode,"Cash Flow","Free Cash Flow")



    } yield {

      // Profitability
      val profitWeighting =getWeighting(profitabilityWeightings)

      val grossProfitRatio =getFinancialRatio(grossProfit)
      val operatingProfitRatio =getFinancialRatio(operatingProfit)
      val netProfitRatio =getFinancialRatio(netProfit)

      val grossProfitIndicator = indicator(grossProfitRatio.target.toString,grossProfitRatio.uom,grossProfitRatio.importance,grossProfitRatio.ftype)
      val operatingProfitIndicator = indicator(operatingProfitRatio.target.toString,operatingProfitRatio.uom,operatingProfitRatio.importance,operatingProfitRatio.ftype)
      val netProfitIndicator = indicator(netProfitRatio.target.toString,netProfitRatio.uom,netProfitRatio.importance,netProfitRatio.ftype)

      val profitabilityKPI = profitabilityKpi(Map("Gross Profit"->grossProfitIndicator,"Operating Profit"->operatingProfitIndicator,"Net Profit"->netProfitIndicator))
      val profitabilityKPA  = profitabilityKpa(profitWeighting.weighting.toString,profitabilityKPI)


      //Returns

      val returnsWeighting =getWeighting(returnsWeightings)

      val returnsOnAssetsRatio =getFinancialRatio(returnsOnAssets)
      val returnOnEquityRatio =getFinancialRatio(returnOnEquity)
      val returnOnCapitalEmployedRatio =getFinancialRatio(returnOnCapitalEmployed)

      val returnsOnAssetsIndicator = indicator(returnsOnAssetsRatio.target.toString,returnsOnAssetsRatio.uom,returnsOnAssetsRatio.importance,returnsOnAssetsRatio.ftype)
      val returnOnEquityIndicator = indicator(returnOnEquityRatio.target.toString,returnOnEquityRatio.uom,returnOnEquityRatio.importance,returnOnEquityRatio.ftype)
      val returnOnCapitalEmployedIndicator = indicator(returnOnCapitalEmployedRatio.target.toString,returnOnCapitalEmployedRatio.uom,returnOnCapitalEmployedRatio.importance,returnOnCapitalEmployedRatio.ftype)

      val returnKPI = profitabilityKpi(Map("Return on Assets"->returnsOnAssetsIndicator,"Return on Equity"->returnOnEquityIndicator,"Return On Capital Employed"->returnOnCapitalEmployedIndicator))
      val returnKPA  = profitabilityKpa(returnsWeighting.weighting.toString,returnKPI)




      // Gearing

      val gearingWeighting =getWeighting(gearingWeightings)

      val debtToEquityRatio =getFinancialRatio(debtToEquity)
      val debtToAssetsRatio =getFinancialRatio(debtToAssets)
      val equityToAssetsRatio =getFinancialRatio(equityToAssets)

      val debtToEquityIndicator = indicator(debtToEquityRatio.target.toString,debtToEquityRatio.uom,debtToEquityRatio.importance,debtToEquityRatio.ftype)
      val debtToAssetsIndicator = indicator(debtToAssetsRatio.target.toString,debtToAssetsRatio.uom,debtToAssetsRatio.importance,debtToAssetsRatio.ftype)
      val equityToAssetsIndicator = indicator(equityToAssetsRatio.target.toString,equityToAssetsRatio.uom,equityToAssetsRatio.importance,equityToAssetsRatio.ftype)

      val gearingKPI = profitabilityKpi(Map("Debt-to-Equity"->debtToEquityIndicator,"Debt-to-Assets"->debtToAssetsIndicator,"Equity-to-Assets"->equityToAssetsIndicator))
      val gearingKPA  = profitabilityKpa(gearingWeighting.weighting.toString,gearingKPI)


      // Liquidity


      val liquidityWeighting =getWeighting(liquidityWeightings)

      val quickRatioRatio =getFinancialRatio(quickRatio)
      val currentRatioRatio =getFinancialRatio(currentRatio)
      val cashRatioRatio =getFinancialRatio(cashRatio)


      val quickRatioIndicator = indicator(quickRatioRatio.target.toString,quickRatioRatio.uom,quickRatioRatio.importance,quickRatioRatio.ftype)
      val currentRatioIndicator = indicator(currentRatioRatio.target.toString,currentRatioRatio.uom,currentRatioRatio.importance,currentRatioRatio.ftype)
      val cashRatioIndicator = indicator(cashRatioRatio.target.toString,cashRatioRatio.uom,cashRatioRatio.importance,cashRatioRatio.ftype)

      val liquidityKPI = profitabilityKpi(Map("Quick Ratio"->quickRatioIndicator,"Current Ratio"->currentRatioIndicator,"Cash Ratio"->cashRatioIndicator))
      val liquidityKPA  = profitabilityKpa(liquidityWeighting.weighting.toString,liquidityKPI)


      // Capital

      val capitalWeighting =getWeighting(capitalWeightings)

      val inventoryTurnsRatio =getFinancialRatio(inventoryTurns)
      val workingCapitalRatio =getFinancialRatio(workingCapital)
      val inventoryDaysRatio =getFinancialRatio(inventoryDays)

      val inventoryTurnsIndicator = indicator(inventoryTurnsRatio.target.toString,inventoryTurnsRatio.uom,inventoryTurnsRatio.importance,inventoryTurnsRatio.ftype)
      val workingCapitalIndicator = indicator(workingCapitalRatio.target.toString,workingCapitalRatio.uom,workingCapitalRatio.importance,workingCapitalRatio.ftype)
      val inventoryDaysIndicator = indicator(inventoryDaysRatio.target.toString,inventoryDaysRatio.uom,inventoryDaysRatio.importance,inventoryDaysRatio.ftype)

      val capitalKPI = profitabilityKpi(Map("Inventory Turns"->inventoryTurnsIndicator,"Working Capital"->workingCapitalIndicator,"Inventory Days"->inventoryDaysIndicator))
      val capitalKPA  = profitabilityKpa(capitalWeighting.weighting.toString,capitalKPI)


      // Cash Flow

      val cashFlowWeighting =getWeighting(cashFlowWeightings)

      val cashFlowMarginRatio =getFinancialRatio(cashFlowMargin)
      val netCashFlowRatio =getFinancialRatio(netCashFlow)
      val freeCashFlowRatio =getFinancialRatio(freeCashFlow)

      val cashFlowMarginIndicator = indicator(cashFlowMarginRatio.target.toString,cashFlowMarginRatio.uom,cashFlowMarginRatio.importance,cashFlowMarginRatio.ftype)
      val netCashFlowIndicator = indicator(netCashFlowRatio.target.toString,netCashFlowRatio.uom,netCashFlowRatio.importance,netCashFlowRatio.ftype)
      val freeCashFlowIndicator = indicator(freeCashFlowRatio.target.toString,freeCashFlowRatio.uom,freeCashFlowRatio.importance,freeCashFlowRatio.ftype)

      val cashFlowKPI = profitabilityKpi(Map("Cash Flow Margin"->cashFlowMarginIndicator,"Net Cash Flow"->netCashFlowIndicator,"Free Cash Flow"->freeCashFlowIndicator))
      val cashFlowKPA  = profitabilityKpa(cashFlowWeighting.weighting.toString,cashFlowKPI)
      Map("Profitability"->profitabilityKPA, "Returns"->returnKPA,"Gearing"->gearingKPA,"Liquidity"->liquidityKPA, "Capital"-> capitalKPA,"Cash Flow"->cashFlowKPA )


    }
  }

  private def getWeighting(weighting: Option[Weighting]): Weighting = {
    weighting match {
      case Some(weighting) => weighting
      case None => Weighting.default
    }
  }

  private def getFinancialRatio(ratio: Option[FinancialRatio]): FinancialRatio = {
    ratio match {
      case Some(user) => user
      case None => FinancialRatio.default
    }
  }
}
