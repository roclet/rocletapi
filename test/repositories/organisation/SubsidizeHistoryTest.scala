package repositories.organisation

import domain.organisation.SubsidizeHistory
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class SubsidizeHistoryTest extends FeatureSpec with GivenWhenThen{

  feature("FundersTest") {
    info("FundersTest")
    info("FundersTest")

    scenario("Create New Funding History") {

      Given("Given a Organisation with ")
      val fundinghistory = SubsidizeHistory("ZFP0992","PTR302",new DateTime(),"active")
      And(" Given the Repository to Persist a Organisation")
      val subsidizehistoryRepo = SubsidizeHistoryRepository
      print(" Object Obtained ", subsidizehistoryRepo)
      When("When I persist a Organisation Documents")
      subsidizehistoryRepo.save(fundinghistory)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(subsidizehistoryRepo.getbyfundeCode("ZFP0992"), 2 minutes)
      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.id == "zw001")
    }
  }
}
