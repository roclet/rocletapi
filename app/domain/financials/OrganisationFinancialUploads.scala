package domain.financials

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/15.
  */
case class OrganisationFinancialUploads(orgCode:String,
                                       filedId:String,
                                       fileUlrl:String,
                                       date:DateTime,
                                       mime:String
                                       )
object OrganisationFinancialUploads{
  implicit val orgFinUpladsFmt = Json.format[OrganisationFinancialUploads]
}
