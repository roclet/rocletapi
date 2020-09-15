package domain.financials

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/01/10.
  */
case class OrganisationBudget(orgCode: String,
                              year: Int,
                              itemCode: String,
                              itemDescription: String,
                              status:String,
                              m1: Float,
                              m2: Float,
                              m3: Float,
                              m4: Float,
                              m5: Float,
                              m6: Float,
                              m7: Float,
                              m8: Float,
                              m9: Float,
                              m10: Float,
                              m11: Float,
                              m12: Float)

object OrganisationBudget {
  implicit val budgetFmt = Json.format[OrganisationBudget]
}
