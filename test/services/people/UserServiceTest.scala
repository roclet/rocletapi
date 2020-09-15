package services.people

import conf.security.AuthUtil
import domain.people.{User, UserStatusChange}
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/09/07.
  */
class UserServiceTest extends FunSuite with BeforeAndAfterEach {

  val email ="cecile@gmail.com"
  val password ="nicko123"
  val orgCode ="JJO0124"
  val users = User(email,"Mia","makashi","nbokashanga",password,"active",orgCode)

  test("createUser"){

    val userdemog = Await.result(UserService.apply.createUser(users), 2.minutes)
    assert(userdemog.isExhausted)
  }
  test("testGetUserRoles") {


    val UserIdenti =Await.result(UserService.apply.getUserRoles(orgCode,email),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserIdentitiesByemail*********** ======>",i))
  }

  test("saveUserStatusChange") {

    val userstatuschange = UserStatusChange(orgCode,email,"123","mpengele@gmail.com",new DateTime,"ndomi123","Merci Diue","active","desactive")

    val result =Await.result(UserService.apply.saveUserStatusChange(userstatuschange),2 minutes)
    assert(result.isExhausted)
  }

  test("testCheckUserAvailability") {
        val UserIdenti =Await.result(UserService.apply.checkUserAvailability(users),2 minutes)
        println("**********testCheckUserAvailability*********** ======>",UserIdenti)
  }
  test("organisationusers") {
    val UserIdenti =Await.result(UserService.apply.getOrganisationUsers(orgCode),2 minutes)
    println("**********testorganisationusers*********** ======>",UserIdenti)
  }

  test("getAllUserStatusChange") {

    val UserIdenti =Await.result(UserService.apply.getAllUserStatusChange(orgCode,email),2 minutes)
    println("**********getAllUserStatusChange*********** ======>",UserIdenti)
  }

  test("testCreateOrUpdateUser") {
    val user = User(
      email,
      "cecile pehanga",
      "makashi", "luckson", "123", "ACTIVE", orgCode)
    val result = Await.result(UserService().updateUser(user), 2.minutes)
    assert(result.isExhausted)
  }

}
