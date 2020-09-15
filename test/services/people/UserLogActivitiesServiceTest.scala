package services.people

import java.util.Date
import java.util.UUID

import conf.util.Util
import domain.people.UserLogActivities
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/23.
  */
class UserLogActivitiesServiceTest extends FunSuite{

  test("testCreateNewUserIdentities") {
    val useridentities = UserLogActivities("MGNT-4BLAV","kumi@test.com",Util.md5Hash(UUID.randomUUID().toString()),"tryry0121","Change update username",new DateTime(),"Change update username")

    val userdemog = Await.result(UserLogActivitiesService.apply.save(useridentities), 2.minutes)
    assert(userdemog.isExhausted)
  }

  test("getUserIdentitiesByemail") {

    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"

    val UserIdenti =Await.result(UserLogActivitiesService.apply.getUserLogActivitiesByemail(orgCode,email),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserIdentitiesByemail*********** ======>",i))
  }
  test("getUserLogActivitiesBySessionId") {

    val sessionId="tryry0121"
    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"

    val UserIdenti =Await.result(UserLogActivitiesService.apply.getUserLogActivitiesBySessionId(orgCode,email,sessionId),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserIdentitiesByemail*********** ======>",i))
  }
}
