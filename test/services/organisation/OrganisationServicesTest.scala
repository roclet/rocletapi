package services.organisation

import domain.organisation.Organisation
import domain.people.User
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by hashcode on 2016/09/07.
  */
class OrganisationServicesTest extends FunSuite {

  test("testGetOrganisationCode") {
    val organisationName ="MM-MMM"
    val OrgCode =Await.result(OrganisationServices.apply.generateOrganisationCode(organisationName),2 minutes)
   println("**********testGetOrganisationCode*********** ======>",OrgCode)
  }

  test("testCheckCodeAvailability") {
    val orgCode ="MGNR-BOONS"
    val organisationa =Await.result(OrganisationServices.apply.checkCodeAvailability(orgCode),2 minutes )
    println("**********intervalue*********** ======>",organisationa)
  }

  test("testGetOrganisations") {

   val listorga =Await.result(OrganisationServices.apply.getOrganisations(10),2 minutes)
    listorga.foreach(i =>println("********** intervalue Organisation *********** ======>",i))
  }

  test("testGetOrganisationByCode") {
    val orgCode ="ENOR-DKGNY"
    val listorga =Await.result(OrganisationServices.apply.getOrganisationByCode(orgCode),2 minutes)
    listorga.foreach(i =>println("**********Organisation code*********** ======>",i))
  }

  test("testCreateOrganisation") {
    val org = Organisation("MM-MMM","Margin Mentor","B021","ACTIVE","mingakuminga@gmail.com",Map())
    val user = User("mingakuminga@gmail.com","kuminga","makashi","roclet","xzyu099xy","active","MM-MMM")
    OrganisationServices.apply.createOrganisation(org)
  }

}
