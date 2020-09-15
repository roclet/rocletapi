package domain.financials

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/11/28. Budget
  * rgcode text,
    year int,
    month int,
    item text,
    amount float,
    fullname text,
    month_desc text,
  */
case class Budget( orgCode: String,
                   year: Int,
                   month: Int,
                   item: String,
                   amount: Float,
                   fullname: String,
                   month_desc: String)

object Budget {
  implicit val budgetFmt = Json.format[Budget]
}
