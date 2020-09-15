package services.organisation

import domain.organisation.OrganisationContact
import domain.people.User
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class OrganisationContactServiceTest extends FunSuite{

  test("OrganisationContact") {

    val orgCont = OrganisationContact("MMM-890","mingakuminga@gmail.com","LK021","I901",Map())
    val service = Await.result(OrganisationContactService.apply.createOrupdate(orgCont),2 minutes)
    assert(service.isExhausted)
  }

  test("findOrganisationContactByorgCode") {
        val orgCode="MMM-890"
        val OrgCode =Await.result(OrganisationContactService.apply.findOrganisationContactByorgCode(orgCode),2 minutes)
    OrgCode.foreach(i =>println("**********findOrganisationContactByorgCode*********** ======>",i))
  }

  test("findOrganisationContactByemail") {
    val orgCode="MMM-890"
    val email="mingakuminga@gmail.com"
    val OrgCode =Await.result(OrganisationContactService.apply.findOrganisationContactByemail(orgCode,email),2 minutes)
    OrgCode.foreach(i =>println("**********findOrganisationContactByemail*********** ======>",i))
  }
  test("findOrganisationContactById") {
    val orgCode="MMM-890"
    val email="mingakuminga@gmail.com"
    val id="LK021"
    val OrgCode =Await.result(OrganisationContactService.apply.findOrganisationContactById(orgCode,email,id),2 minutes)
    OrgCode.foreach(i =>println("**********findOrganisationContactById*********** ======>",i))
  }
}
