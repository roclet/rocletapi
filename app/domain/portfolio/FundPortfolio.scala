package domain.portfolio

import play.api.libs.json.Json
import services.portfolio.FundPortfolioService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/11.
  */
case class FundPortfolio(fundOrgCode: String, smeOrgCode: String){
  def getCurrentStatus :Future[String] = FundPortfolio.currentStatus(this)
}

object FundPortfolio {

  implicit val fundPortfolioFMT = Json.format[FundPortfolio]
  def currentStatus(model: FundPortfolio):Future[String] = {
    FundPortfolioService.apply.getCurrentStatus(model) map( status => status.status )
  }
}
