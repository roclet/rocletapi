package domain.address

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/03/02.
  */
case class Cordinates(latititude: Double, longitude: Double, location: String)

object Cordinates {

  implicit val location = Json.format[Cordinates]
}
