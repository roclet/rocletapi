package repositories.organisation

import domain.organisation.Subsidize
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class SubsidizeTest extends FeatureSpec with GivenWhenThen{

  feature("FundersTest") {
    info("FundersTest")
    info("FundersTest")

    scenario("Create New Funders") {

      Given("Given a Organisation with findCode, organisation")
      val funders = Subsidize("ZUP0992", "Cape Pensula University")
      And(" Given the Repository to Persist a Organisation")
      val subsidizerepositoryRepo = SubsidizeRepository
      print(" Object Obtained ", subsidizerepositoryRepo)
      When("When I persist a Organisation Documents")
      subsidizerepositoryRepo.save(funders)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(subsidizerepositoryRepo.getfindCode("ZUP0992"), 2 minutes)
      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.id == "zw001")
    }
  }
}
