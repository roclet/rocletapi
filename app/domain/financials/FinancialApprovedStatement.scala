package domain.financials

import java.util.Date

import com.websudos.phantom.dsl.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/12/19.
  */
case class FinancialApprovedStatement(
                                       orgCode: String,
                                       id: String,
                                       statementType: String,
                                       date: Date,
                                       category: String,
                                       description: String,
                                       subCategory: String,
                                       amount: BigDecimal,
                                       month: Int,
                                       year: Int,
                                       dateCreated: DateTime
                                     )

object FinancialApprovedStatement {
  implicit val finStmtFmt = Json.format[FinancialApprovedStatement]
}
