package domain.people

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class UserStatusChange(orgCode:String,
                             email:String,
                            id:String,
                            changedBy:String,
                            date:DateTime,
                            sessionId:String,
                            description:String,
                            oldStatus:String,
                            newStatus:String) {
}


object UserStatusChange{
    implicit val userstaChange = Json.format[UserStatusChange]
}
