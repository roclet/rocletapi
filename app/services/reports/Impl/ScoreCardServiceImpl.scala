package services.reports.Impl

import domain.reports.ScoreCard
import services.reports.ScoreCardService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ScoreCardServiceImpl extends ScoreCardService{
  override def getScoreCard(orgcode:String, period: String, year: Int, month: Int): Future[ScoreCard] = {


    val profitability = Map("gross-profit"->1, "operating-profit"-> 1, "net-profit"-> 1, "slice"-> 1)

    val cashflow=Map("free-cash-flow"->1, "net-cash-flow"-> 1, "cash-flow-margin"-> 1, "slice"-> 1)

    val capital=Map("inventory-turns"->1, "working-capital"-> 1, "inventory-days"-> 1, "slice"-> 1)

    val returns = Map("return-on-assets"->1, "return-on-equity"-> 1, "return-on-capital"-> 1, "slice"-> 1)

    val gearing=Map("debt-to-equity"->1, "debt-to-assets"-> 1, "equity-to-assets"-> 1, "slice"-> 1)

    val liquidity=Map("quick-ratio"->1, "current-ratio"-> 1, "cash-ratio"-> 1, "slice"-> 1)


    Future{
      ScoreCard(Map(
        "profitability"->profitability,
        "cash-flow"->cashflow,
        "capital"->capital,
        "returns"->returns,
        "gearing"->gearing,
        "liquidity"->liquidity),
        "C",50.5)
    }
  }
}
