package domain.people

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserContact(orgCode: String,
                       email: String,
                       id: String,
                       contactTypeId: String,
                       details: Map[String, String]) {


}

object UserContact {

  implicit val userContactsFmt = Json.format[UserContact]
  def default = UserContact("","","","",Map())
}
