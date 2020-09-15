package domain.util

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class IdentityTypes(id:String,value:String)

object IdentityTypes {
  implicit val identitykeysFmt = Json.format[IdentityTypes]
}