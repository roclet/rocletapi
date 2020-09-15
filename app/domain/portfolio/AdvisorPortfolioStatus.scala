package domain.portfolio

import com.websudos.phantom.dsl.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/11/12.
  */


case class AdvisorPortfolioStatus(email:String,smeOrgCode:String,date:DateTime,status:String)

object AdvisorPortfolioStatus{
  implicit val advisorPortfolioFMT=Json.format[AdvisorPortfolioStatus]

}
