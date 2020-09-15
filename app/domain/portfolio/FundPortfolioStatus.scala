package domain.portfolio


import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/11/12.
  */
case class FundPortfolioStatus(fundOrgCode: String, smeOrgCode: String,date:DateTime,status:String)

object FundPortfolioStatus{
  implicit val advisorPortfolioFMT=Json.format[FundPortfolioStatus]
}
