package domain.financials

import java.util.Date

import com.websudos.phantom.dsl.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/15.
  */
case class FinancialStatementRecords(orgCode:String,
                                     id:String,
                                    statementType:String,
                                    date:Date,
                                    category:String,
                                    description:String,
                                    subCategory:String,
                                    amount:BigDecimal,
                                    month:Int,
                                    year:Int,
                                    dateCreated:DateTime
                                    )
object FinancialStatementRecords{
  implicit val finStmtFmt = Json.format[FinancialStatementRecords]
}
