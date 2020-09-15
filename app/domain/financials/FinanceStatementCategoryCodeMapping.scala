package domain.financials

import java.util.Date
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/20.
  */
case class FinanceStatementCategoryCodeMapping(orgCode:String,
                                               id:String,
                                               financetype:String,
                                               category:String,
                                               subcategory:String,
                                               startCode:Long,
                                               endCode:Long,
                                               sessionId:String,
                                               date:Date
                                  )
object FinanceStatementCategoryCodeMapping{
  implicit val IncomeStatCatFmt = Json.format[FinanceStatementCategoryCodeMapping]
}
