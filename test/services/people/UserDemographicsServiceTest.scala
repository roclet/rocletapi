package services.people


import java.util.Date

import domain.people.UserDemographics
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/23.
  */
class UserDemographicsServiceTest extends FunSuite{

  test("testCreateNewUserDemographics") {


    val userdemographic = UserDemographics("MGNT-4BLAV","kumi@test.com","ce098","miss",new Date(),"12")

    val userdemog = Await.result(UserDemographicsService.apply.save(userdemographic), 2.minutes)
    assert(userdemog.isExhausted)
  }
  test("getUserDemographicsByemail") {
    val orgCode="MGNT-4BLAV"
    val email="kumi@test.com"
    val OrgCode =Await.result(UserDemographicsService.apply.getUserDemographicsByemail(orgCode,email),2 minutes)
    OrgCode.foreach(i =>println("**********getUserDemographicsByemail*********** ======>",i))
  }
  test("getAllUserContactByorgCode") {
    val orgCode="MGNT-4BLAV"

    val Demog =Await.result(UserDemographicsService.apply.getUserDemographicByOrgCode(orgCode),2 minutes)
    Demog.foreach(i =>println("**********getUserDemographicsByemail*********** ======>",i))
  }
}
