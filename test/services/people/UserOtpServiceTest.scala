package services.people

import java.util.UUID

import conf.util.Util
import domain.people.UserOtp
import domain.people.UserOptEvent
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/24.
  */
class UserOtpServiceTest extends FunSuite{

  test("testCreateNewUserOtp") {
    val useropt = UserOtp("MGNT-4BLAV","kumi@test.com",
      "opt012","SMS",
      "122290","11111",new DateTime(),new DateTime(),"active")

    val userdemog = Await.result(UserOtpService.apply.saveUserOtp(useropt), 2.minutes)
    assert(userdemog.isExhausted)
  }

  test("testSaveUserOptEvent") {

    val useroptevent = UserOptEvent("opt012","901",new DateTime(),"Login cust")

    val useropteve =Await.result(UserOtpService.apply.saveOtpEvent(useroptevent),2 minutes)
    assert(useropteve.isExhausted)
  }
  test("getCurrentUserOtp") {

    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"

    val Useropt =Await.result(UserOtpService.apply.getCurrentUserOtp(orgCode,email),2 minutes)
    println("**********getCurrentUserOtp*********** ======>")
  }
  test("getOptEvents") {

    val UserOtpId="opt012"

    val UserIdenti =Await.result(UserOtpService.apply.getOptEvents(UserOtpId),2 minutes)
    UserIdenti.foreach(i =>println("**********getOptEvents*********** ======>",i))
  }
  test("getOtpEventById") {

    val UserOtpId="opt012"
    val id="901"
    val UserIdenti =Await.result(UserOtpService.apply.getOtpEventById(UserOtpId,id),2 minutes)
    UserIdenti.foreach(i =>println("**********getOtpEventById*********** ======>",i))
  }

  test("getUserOtps") {

    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"

    val UserIdenti =Await.result(UserOtpService.apply.getUserOtps(orgCode,email),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserOtps*********** ======>",i))
  }
  test("getUserOtpByOtpId") {

    val userOtpId="opt012"
    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"

    val UserIdenti =Await.result(UserOtpService.apply.getUserOtpByOtpId(orgCode,email,userOtpId),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserOtpByOtpId*********** ======>",i))

  }
}
