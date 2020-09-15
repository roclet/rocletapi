package domain.hr

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/04/06.
  */
case class Nationality(code:String, name:String)

object Nationality{

  implicit val nationaliTyFMT= Json.format[Nationality]

}
