package conf.security

import domain.people.User
import services.people.UserService
import services.util.TokenService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/06.
  */
object LoggedInUser {
  def user(orgCode: String,email:String): Future[Option[User]] = {
    UserService.apply().getUserByEmail(orgCode, email)
  }
}
