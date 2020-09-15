package domain.syslogs

import com.websudos.phantom.dsl.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class SystemLogEvents(orgCode: String,
                           id: String,
                           eventName: String,
                           eventType: String,
                           message: String,
                           date: DateTime) {

}
object SystemLogEvents {
  implicit val syseventLog = Json.format[SystemLogEvents]
}
