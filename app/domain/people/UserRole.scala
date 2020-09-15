package domain.people

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserRole( orgCode:String,
                     email: String,
                     roleId: String
                   )

object UserRole {
  implicit val userroleFmt = Json.format[UserRole]
}
