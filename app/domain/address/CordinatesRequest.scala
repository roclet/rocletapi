package domain.address

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/03/02.
  */
case class CordinatesRequest(request:String)

object CordinatesRequest{
  implicit val location = Json.format[CordinatesRequest]
}

