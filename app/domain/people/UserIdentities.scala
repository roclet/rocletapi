package domain.people

import java.util.Date
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserIdentities(orgCode: String,
                          email: String,
                          id: String,
                          idtype: String,
                          idValue: String,
                          issuedDate: Date,
                          expirationDate: Date,
                          countryOfIssue: String
                         )

object UserIdentities {
  implicit val useridenFmt = Json.format[UserIdentities]
}