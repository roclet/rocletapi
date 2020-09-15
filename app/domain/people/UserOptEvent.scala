package domain.people

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/08/30.
  */
case class UserOptEvent(userOtpId:String,
                   id:String,
                   eventTime:DateTime,
                   eventName:String) {

}
object UserOptEvent{
  implicit val useropteventFmt = Json.format[UserOptEvent]
}
