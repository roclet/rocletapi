package repositories.organisation


import domain.organisation.OrganisationAddress
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/19.
  */
class OrganisationAddressTest extends FeatureSpec with GivenWhenThen{

  feature("OrganisationAddressTest") {
    info("OrganisationAddressTest")
    info("OrganisationAddressTest")

    scenario("Organisation Address") {

      Given("Given a Organisation with orgCode, email,id,locationId,addressId and details ")
      val details = Map("Company" -> "Metswalle Mentor", "registration number" -> "00333", "Located" -> "Civil Cente")
      val Organisationddress = OrganisationAddress("ZP0992", "deve@gmail.com","111", "333","9090",details)
      And(" Given the Repository to Persist a Organisation")
      val OrganisationAddrRepo = OrganisationAddressRepository
      print(" Object Obtained ", OrganisationAddrRepo)
      When("When I persist a Organisation Documents")
      OrganisationAddrRepo.save(Organisationddress)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(OrganisationAddrRepo.getOrgAddressByorgCode("ZP0992"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrgCode = Await.result(OrganisationAddrRepo.getOrgAddressByemail("ZM0992","deve@gmail.com"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrgdelete = Await.result(OrganisationAddrRepo.getOrgAddressById("ZM0992","deve@gmail.com","111"), 2 minutes)

      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.id == "zw001")
    }
  }

}
