package domain.util

import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/09/01.
  */
case class ApiKeys(id: String, value: String)

object ApiKeys {
  implicit val apiKeysFmt = Json.format[ApiKeys]
}
