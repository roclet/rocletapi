package domain.reports

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/04/12.
  */
case class FinancialRatio(orgcode: String,
                          kpa: String,
                          kpi: String,
                          target: Double,
                          uom: String,
                          importance: String,
                          ftype: String
                         )

object FinancialRatio {
  implicit val weightingFmt = Json.format[FinancialRatio]
  def default:FinancialRatio = FinancialRatio("","","",0.00,"","","")

}


