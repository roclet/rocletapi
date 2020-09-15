package domain.hr

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/04/06.
  */
case class Position(positionId: String, name: String)

object Position {

  implicit val positionFmt = Json.format[Position]

}
