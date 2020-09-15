package domain.organisation

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/09/14.
  */
case class SubsidizeHistory(funderCode:String, orgCode:String,date:DateTime,status:String) {

}
object SubsidizeHistory{
    implicit val subsidizeshistFmt=Json.format[SubsidizeHistory]
}