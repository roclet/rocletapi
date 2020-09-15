package domain.people

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class User(email: String,
                firstname: String,
                lastname: String,
                middlename: String,
                password: String,
                userStatus: String,
                orgCode: String
               )
object User {
  implicit val userFmt = Json.format[User]
  def default = User("","","","","","","")
}
