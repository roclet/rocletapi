package domain.util

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/03/04.
  */
case class ExternalRoles(id: String,rolename: String,privileges: Set[String])

object ExternalRoles {

  implicit val rolesFmt = Json.format[ExternalRoles]
}
