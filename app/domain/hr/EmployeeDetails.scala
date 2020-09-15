package domain.hr

import domain.people._
import play.api.libs.json.{Json, Writes}

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/14.
  */
case class EmployeeDetails(
                            user: User,
                            address: Seq[UserAddress],
                            contacts: Seq[UserContact],
                            demographics: Option[UserDemographics],
                            identities: Seq[UserIdentities],
                            roles: Seq[UserRole],
                            employee: Option[Employee]
                          )

object EmployeeDetails {

  implicit val kpaWrites = new Writes[EmployeeDetails] {
    def writes(employee: EmployeeDetails) = Json.obj(
      "user" -> employee.user,
      "address" -> employee.address,
      "contacts" -> employee.contacts,
      "demographics" -> employee.demographics,
      "identities" -> employee.identities,
      "roles" -> employee.roles,
      "employee" -> employee.employee
    )
  }

}
