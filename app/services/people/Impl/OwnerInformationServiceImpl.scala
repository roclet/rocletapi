package services.people.Impl

import conf.util.MarginKeys
import domain.people.{OwnerInformation, User, UserContact}
import services.people.{OwnerInformationService, UserContactService, UserService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  *             email: String,
                firstname: String,
                lastname: String,
                middlename: String,
                password: String,
                userStatus: String,
                orgCode: String
                orgCode: String,
                email: String,
                id: String,
                contactTypeId: String,
                details: Map[String, String]
  */
class OwnerInformationServiceImpl extends OwnerInformationService {

  override def save(orgCode: String, employee: OwnerInformation): Future[String] = {
    for {
      user <- UserService.apply().getUserByEmail(orgCode, employee.details.getOrElse("email", ""))
      saved <- UserService.apply().updateUser(getUser(user)
        .copy(firstname = employee.details.getOrElse("firstname", ""),
          lastname = employee.details.getOrElse("lastname", "")))
      saveAddress <- UserContactService.apply.createOrupdate(getContact(user, employee))

    } yield {
      "Ok"
    }
  }

  override def getOwnerInformation(orgCode:String): Future[OwnerInformation] = {
    for{
      user <- UserService.apply().getOrganisationOwner(orgCode)// GET BUSINESS OWNER
      contact <-UserContactService.apply.getAllUserContactByid(orgCode,user.email,MarginKeys.OWNERINFO)
    } yield {
      val details = Map(
        "email"-> user.email,
        "firstname"-> user.firstname,
        "lastname"-> user.lastname,
        "mobile"-> getContact(contact).details.getOrElse("mobile", ""),
        "landline"-> getContact(contact).details.getOrElse("landline", ""),
        "designation"-> getContact(contact).details.getOrElse("designation", "")
      )
      OwnerInformation(user.email, details)
    }
  }

  def getContact(user: Option[User], employee: OwnerInformation): UserContact = {
    val details = Map(
      "mobile"-> employee.details.getOrElse("mobile", ""),
      "landline"-> employee.details.getOrElse("landline", ""),
      "designation"-> employee.details.getOrElse("designation", "")
    )

    UserContact(getUser(user).orgCode,
      getUser(user).email,
      MarginKeys.OWNERINFO,MarginKeys.OWNERINFO,details)
  }

  private def getUser(user: Option[User]): User = {
    user match {
      case Some(user) => user
      case None => User.default
    }
  }

  private def getContact(user: Option[UserContact]): UserContact = {
    user match {
      case Some(contact) => contact
      case None => UserContact.default
    }
  }
}


