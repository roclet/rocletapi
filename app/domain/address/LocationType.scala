package domain.address

import play.api.libs.json.Json

case class LocationType(id:String, name:String,code:String,state:String)

object LocationType{
  implicit val locatFmt = Json.format[LocationType]
}