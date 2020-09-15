package domain.subscriptions

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
// Needs Enhancement
case class Subscriptions(id:String,
                         subType:String,
                         decription:String,
                         cost:BigDecimal) {
}
object Subscriptions{
   implicit val subscriptFmt = Json.format[Subscriptions]
}