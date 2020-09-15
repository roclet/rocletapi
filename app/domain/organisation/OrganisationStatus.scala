package domain.organisation

import com.websudos.phantom.dsl.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/11/13.
  */
case class OrganisationStatus(orgCode: String, date: DateTime,status:String,detail:String)

object OrganisationStatus {
  implicit val organFmt = Json.format[OrganisationStatus]
}
