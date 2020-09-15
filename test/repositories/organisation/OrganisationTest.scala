package repositories.organisation


import domain.organisation.Organisation
import org.scalatest.{FeatureSpec, GivenWhenThen}


import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/08/28.
  */
class OrganisationTest extends FeatureSpec with GivenWhenThen{

  feature("Upload New Organisation") {
    info("As a System Admin")
    info("Upload New  Organisation")

    scenario("Organisation Upload files") {
      val details = Map("Company" -> "Metswalle Mentor", "registration number" -> "00333", "Located" -> "Civil Cente")

      Given("Given a Organisation with orgCode, name,orgTypeId,status,contactemail and details ")

      val organisation = Organisation("ZM0994", "Metswalle","23" ,"Active","roclet@gmail.com",details)
      And(" Given the Repository to Persist a Organisation")
      val OrganisationRepo = OrganisationRepository
      print(" Object Obtained ", OrganisationRepo)
      When("When I persist a Organisation")
      OrganisationRepo.save(organisation)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisation = Await.result(OrganisationRepo.getOrganisationByCode("ZM0994"), 2 minutes)
      Then("The Values must validate ")
      val retrievedOrg1 = Await.result(OrganisationRepo.getAllOrganisations(0), 2 minutes)

      val retrievedOrg = Await.result(OrganisationRepo.getAllOrganisationsByTypeId("ZM0994","23"), 2 minutes)
      And(" Assertion is ")
//      assert(retrievedOrganisation.get.orgCode == "ZM0992")
    }
  }
}
