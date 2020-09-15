package services.people

import domain.people.UserContact
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/23.
  */
class UserContactServiceTest extends FunSuite{

  test("testCreateNewUserContact") {
        val usercontact = UserContact("HY098","john@test.com","test1903","AD12",Map())

        val useraddr = Await.result(UserContactService.apply.createOrupdate(usercontact), 2.minutes)
        assert(useraddr.isExhausted)
  }
  test("getAllUserContactByorgCode") {
        val orgCode="HY098"
        val OrgCode =Await.result(UserContactService.apply.getAllUserContactByorgCode(orgCode),2 minutes)
        OrgCode.foreach(i =>println("**********getAllUserContactByorgCode*********** ======>",i))
  }
  test("getAllUserContactByemail") {
        val orgCode="HY098"
        val email="john@test.com"

        val OrgCode =Await.result(UserContactService.apply.getAllUserContactByemail(orgCode,email),2 minutes)
        OrgCode.foreach(i =>println("**********getAllUserContactByemail*********** ======>",i))
  }
  test("getAllUserContactByid"){
    val orgCode="HY098"
    val email="john@test.com"
    val id="test1903"

    val OrgCodep =Await.result(UserContactService.apply.getAllUserContactByid(orgCode,email,id),2 minutes)
    OrgCodep.foreach(i =>println("**********getAllUserContactByid*********** ======>",i))
  }
}
