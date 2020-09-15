package services.people

import domain.people.UserAddress

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/23.
  */
class UserAddressServiceTest extends FunSuite{

  test("testCreateNewUserAddress") {

    val useraddress = UserAddress("HY098", "john@test.com", "test123", "AD12", Map())

    val useraddr = Await.result(UserAddressService.apply.createOrupdate(useraddress), 2.minutes)
    assert(useraddr.isExhausted)
  }
  test("findUserAddressByOrg") {

        val orgCode="HY098"
        val OrgCode =Await.result(UserAddressService.apply.findUserAddressByOrg(orgCode),2 minutes)
        OrgCode.foreach(i =>println("**********findUserAddressByOrg*********** ======>",i))
  }
  test("findUserAddressByEmail") {

      val orgCode="HY098"
      val email="john@test.com"

      val OrgCode =Await.result(UserAddressService.apply.findUserAddressByEmail(orgCode,email),2 minutes)
      OrgCode.foreach(i =>println("**********findUserAddressByEmail*********** ======>",i))

  }
  test("findUserAddressById") {

      val orgCode="HY098"
      val email="john@test.com"
      val id="test123"

      val OrgCode =Await.result(UserAddressService.apply.findUserAddressById(orgCode,email,id),2 minutes)
      OrgCode.foreach(i =>println("**********findUserAddressById*********** ======>",i))
  }
}
