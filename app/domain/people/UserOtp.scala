package domain.people

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class UserOtp(orgCode:String,
                    email:String,
                   userOtpId:String,
                   channel:String,
                   sessionId:String,
                   pin:String,
                   date: DateTime,
                   responseTime:DateTime,
                   status:String) {

}
object UserOtp{
   implicit val useroptFmt=Json.format[UserOtp]
}