package repositories.organisation

import domain.organisation.OrganisationType
import org.scalatest.{FeatureSpec, GivenWhenThen}


import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class OrganisationTypeTest extends FeatureSpec with GivenWhenThen{

  feature("Organisation New Type") {
    info("Organisation New Type")
    info("Organisation New Type")

    scenario("Organisation New Type") {

      Given("Given a Organisation with id, name")

      val organisationtype = OrganisationType("09321", "Metswalle")
      And(" Given the Repository to Persist a Organisation")
      val organisationtypeRepo = OrganisationTypeRepository
      print(" Object Obtained ", organisationtypeRepo)
      When("When I persist a Organisation")
      organisationtypeRepo.save(organisationtype)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisation = Await.result(organisationtypeRepo.getByOrganisationTypeId("09321"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrg = Await.result(organisationtypeRepo.getAllOrg, 2 minutes)
      And(" Assertion is ")
      //      assert(retrievedOrganisation.get.orgCode == "ZM0992")
    }
  }
}
