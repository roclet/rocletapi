package domain.financials

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/15.
  */
case class OrganisationFinancialUploadsEvents(
                                             fileId:String,
                                             status:String,
                                             date:DateTime,
                                             description:String
                                             )
object OrganisationFinancialUploadsEvents{

  implicit val organisationFmt = Json.format[OrganisationFinancialUploadsEvents]

}
