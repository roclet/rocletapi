package repositories.organisation

import domain.organisation.OrganisationContact
import org.joda.time
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by kuminga on 2016/09/19.
  */
class OrganisationContactTest extends FeatureSpec with GivenWhenThen{

  feature("OrganisationContactTest") {
    info("OrganisationContactTest")
    info("OrganisationContactTest")

    scenario("Organisation Contact") {

      Given("Given a Organisation with orgCode, email,id,contactId")
      val details = Map("Company" -> "Metswalle Mentor", "registration number" -> "00333", "Located" -> "Civil Cente")
      val Organisationcontact = OrganisationContact("ZP0992", "deve@gmail.com","1123", "67333",details)
      And(" Given the Repository to Persist a Organisation")
      val OrganisationConctRepo = OrganisationContactRepository
      print(" Object Obtained ", OrganisationConctRepo)
      When("When I persist a Organisation Documents")
      OrganisationConctRepo.save(Organisationcontact)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(OrganisationConctRepo.getOrganisationContactByorgCode("ZP0992"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrgCode = Await.result(OrganisationConctRepo.getOrganisationContactById("ZM0992","deve@gmail.com","1123"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrgdelete = Await.result(OrganisationConctRepo.getOrganisationContactByemail("ZM0992","deve@gmail.com"), 2 minutes)

      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.id == "zw001")
    }
  }
}
