package domain.organisation

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class OrganisationType(id: String, name: String)

object OrganisationType {
  implicit val organz = Json.format[OrganisationType]
}
