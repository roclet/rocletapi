package repositories.subscriptions

import domain.subscriptions.Subscriptions
import org.joda.time
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/05.
  */
class subscriptionsTest extends FeatureSpec with GivenWhenThen{

  feature("Organisation Subscriptions") {
    info("As a System Admin")
    info("I want to Create a new Organisation Subscriptions")
    info("So that I can Subscribe a Organisation")
    scenario("New Organisation Subscriptions Creation") {

      Given("Given a Subscriptions with id,subType,decription,cost")

      val organisationSubscr = Subscriptions("ZM0001","Cars","Secret dEtat",6000)
      And(" Given the Repository to Persist a Organisation")
      val SubscriptionsRepo = SubscriptionsRepository
      print(" Object Obtained ", SubscriptionsRepo)
      When("When I persist a Organisation Documents")
      SubscriptionsRepo.save(organisationSubscr)
      println(" The Save Executed ")
      val retrievedOrgall = Await.result(SubscriptionsRepo.getSubscriptionsid("ZM0001"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(SubscriptionsRepo.findAll, 2 minutes)
      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.subscriptionsId == "SD1034")findAll
    }
  }
}
