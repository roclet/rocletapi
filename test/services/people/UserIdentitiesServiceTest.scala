package services.people

import java.util.Date
import java.util.UUID

import conf.util.Util
import domain.people.UserIdentities
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/23.
  */
class UserIdentitiesServiceTest extends FunSuite{

  test("testCreateNewUserIdentities") {
    val useridentities = UserIdentities("MGNT-4BLAV","kumi@test.com",Util.md5Hash(UUID.randomUUID().toString()),"PassPort","Merci",new Date(),new Date(),"Sout Sfrica")

    val userdemog = Await.result(UserIdentitiesService.apply.save(useridentities), 2.minutes)
    assert(userdemog.isExhausted)
  }
  test("getUserIdentitiesByemail") {

    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"

    val UserIdenti =Await.result(UserIdentitiesService.apply.getUserIdentitiesByemail(orgCode,email),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserIdentitiesByemail*********** ======>",i))
  }
  test("getUserIdentitiesByorgCode") {

    val orgCode="MGNT-4BLAV"

    val UserIdenti =Await.result(UserIdentitiesService.apply.getUserIdentitiesByorgCode(orgCode),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserIdentitiesByorgCode*********** ======>",i))
  }

  test("getUserIdentitiesById") {


    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"
    val id="b8e36d46ba24161dbeab999db485ba17"
    val UserIdenti =Await.result(UserIdentitiesService.apply.getUserIdentitiesById(orgCode,email,id),2 minutes)
    UserIdenti.foreach(i =>println("**********getUserIdentitiesById*********** ======>",i))
  }
}
