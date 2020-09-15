package domain.portfolio

import play.api.libs.json.Json
import services.portfolio.FundManagerService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */
case class FundManager(fundOrgCode:String,email:String,smeOrgCode:String){
  def getCurrentStatus :Future[String] = FundManager.currentStatus(this)
}

object FundManager{
  implicit val advisorPortfolioFMT=Json.format[FundManager]
  def currentStatus(model: FundManager):Future[String] = {
    FundManagerService.apply.getCurrentStatus(model) map( status => status.status )
  }
}
