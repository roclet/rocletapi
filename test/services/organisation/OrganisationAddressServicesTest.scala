package services.organisation


import domain.organisation.OrganisationAddress
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/19.
  */
class OrganisationAddressServicesTest extends FunSuite{

   test("testcreateOrupdate"){
      val event = OrganisationAddress("BPO124","mingakuminga@gmail.com","7011","l0123","dr123",Map())
      val service = Await.result(OrganisationAddressServices.apply.createOrupdate(event),2 minutes)
      assert(service.isExhausted)
   }
   test("findOrgAddressByorgCode"){
      val orgCode="BPO124"
      val idcode =Await.result(OrganisationAddressServices.apply.findOrgAddressByorgCode(orgCode),2 minutes)
      idcode.foreach(i =>println("**********All Address Type*********** ======>",i))
   }
   test("findOrgAddressByemail"){
      val orgCode="BPO124"
      val email="mingakuminga@gmail.com"
      val idcode =Await.result(OrganisationAddressServices.apply.findOrgAddressByemail(orgCode,email),2 minutes)
      idcode.foreach(i =>println("**********All Address Type*********** ======>",i))
   }
   test(""){
      val orgCode="BPO124"
      val email="mingakuminga@gmail.com"
      val id="7011"
      val idcode =Await.result(OrganisationAddressServices.apply.findOrgAddressById(orgCode,email,id),2 minutes)
      idcode.foreach(i =>println("**********All Address Type*********** ======>",i))
   }
}
