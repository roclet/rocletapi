package domain.people

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserAddress(orgCode: String,
                       email: String,
                       id: String,
                       addressTypeId: String,
                       details: Map[String, String]
                      )

object UserAddress {
  implicit val addresstypeFmt = Json.format[UserAddress]
}
