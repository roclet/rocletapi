package domain.people

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class UserDemographics(orgCode: String,
                            email: String,
                            raceId: String,
                            title: String,
                            dob: Date,
                            genderId: String
                           )

object UserDemographics {
  implicit val userdemFmt = Json.format[UserDemographics]
}
