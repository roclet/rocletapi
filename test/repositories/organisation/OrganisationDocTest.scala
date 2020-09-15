package repositories.organisation

import domain.organisation._
import org.joda.time
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/08/28.
  */
class OrganisationDocTest extends FeatureSpec with GivenWhenThen{

  feature("Create Doc Organisation") {
    info("As a System Admin")
    info("I want to Create a new Organisation")
    info("So that I can Upload Files")

    scenario("Organisation Creation") {

      Given("Given a Organisation with orgCode, url,docType,date and extension ")

      val organisationDoc = OrganisationDocuments("ZM0992", "longterraim.png", "Passport",new time.DateTime(),"png")
      And(" Given the Repository to Persist a Organisation")
      val OrganisationDocRepo = OrganisationDocRepository
      print(" Object Obtained ", OrganisationDocRepo)
      When("When I persist a Organisation Documents")
      OrganisationDocRepo.save(organisationDoc)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(OrganisationDocRepo.findById("ZM0992","passwodul.png"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrgCode = Await.result(OrganisationDocRepo.getOrganisationsDocByCode("ZM0992"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrgdelete = Await.result(OrganisationDocRepo.deleteById("ZM0992","longterraim.png"), 2 minutes)

      And(" Assertion is ")
//      assert(retrievedOrganisationDoc.get.id == "zw001")
    }
  }
}
