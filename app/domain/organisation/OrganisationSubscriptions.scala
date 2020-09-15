package domain.organisation

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class OrganisationSubscriptions(orgCode: String,
                                     id: String,
                                     subscriptionsId: String,
                                     startDate: DateTime,
                                     endDate: DateTime,
                                     status: String
                                    ) {

}

object OrganisationSubscriptions {
  implicit val orgsubFmt = Json.format[OrganisationSubscriptions]
}