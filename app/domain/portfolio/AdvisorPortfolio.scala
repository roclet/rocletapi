package domain.portfolio

import play.api.libs.json.Json
import services.portfolio.AdvisorPortfolioService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future


/**
  * Created by hashcode on 2016/11/11.
  */
case class AdvisorPortfolio(email: String, smeOrgCode: String){
  def getCurrentStatus :Future[String] = AdvisorPortfolio.currentStatus(this)
}
object AdvisorPortfolio {
  implicit val advisorPortfolioFMT = Json.format[AdvisorPortfolio]
  def currentStatus(model: AdvisorPortfolio):Future[String] = {
   AdvisorPortfolioService.apply.getCurrentStatus(model) map( status => status.status )
  }
}
