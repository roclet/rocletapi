package domain.portfolio

import com.websudos.phantom.dsl._
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/11/12.
  */
case class FundManagerStatus(fundOrgCode:String,email:String,smeOrgCode:String,date:DateTime,status:String)

object FundManagerStatus{
  implicit val advisorPortfolioFMT=Json.format[FundManagerStatus]
}
