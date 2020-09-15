package domain.people

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserLogActivities(
                              orgCode:String,
                              email: String,
                             id: String,
                             sessionId: String,
                             details: String,
                             date: DateTime,
                             description: String) {

}

object UserLogActivities {
  implicit val userlogactFmt = Json.format[UserLogActivities]
}
