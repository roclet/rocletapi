package repositories.organisation

import domain.organisation.OrganisationSubscriptions
import org.joda.time
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/08/29.
  */
class OrganisationSubscrTest extends FeatureSpec with GivenWhenThen{


  feature("Organisation Subscriptions") {
    info("As a System Admin")
    info("I want to Create a new Organisation Subscriptions")
    info("So that I can Subscribe a Organisation")
    scenario("New Organisation Subscriptions Creation") {

      Given("Given a Subscriptions with orgCode,id,subscriptions,startDate,endDate and status ")

      val organisationSubscr = OrganisationSubscriptions("ZM0994", "ZMP0901","SD1034",new time.DateTime(),new time.DateTime(),"active")
      And(" Given the Repository to Persist a Organisation")
      val OrganisationSubscrRepo = OrganisationSubscrRepository
      print(" Object Obtained ", OrganisationSubscrRepo)
      When("When I persist a Organisation Documents")
      OrganisationSubscrRepo.save(organisationSubscr)
      println(" The Save Executed ")
      val retrievedOrgall = Await.result(OrganisationSubscrRepo.findAll("ZM0994"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(OrganisationSubscrRepo.getOrgSubcriptById("ZM0994","SD1034","ZMP0901"), 2 minutes)

      And(" Assertion is ")
//      assert(retrievedOrganisationDoc.get.subscriptionsId == "SD1034")getAll
    }
  }
}
