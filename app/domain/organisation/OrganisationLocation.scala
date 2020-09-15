package domain.organisation

import java.util.Date

import com.websudos.phantom.dsl.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/02/25.
  */
case class OrganisationLocation(orgCode:String,
                                date:DateTime,
                                locationId:String,
                                event:String
                                )

object OrganisationLocation{
  implicit val location = Json.format[OrganisationLocation]

}
