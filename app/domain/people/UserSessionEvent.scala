package domain.people

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/20.
  */
case class UserSessionEvent(sessionId: String,
                            id: String,
                            eventTime: DateTime,
                            eventName: String
                      ) {

}

object UserSessionEvent {
  implicit val tokeeventsFmt = Json.format[UserSessionEvent]
}
