package domain.organisation

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/14.
  */
case class Subsidize(findCode:String,organisation:String) {

}
object Subsidize{
   implicit val fundsFmt=Json.format[Subsidize]
}
