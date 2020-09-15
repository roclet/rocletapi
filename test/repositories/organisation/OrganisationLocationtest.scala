package repositories.organisation

import java.util.Date

import domain.organisation.OrganisationLocation
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by kuminga on 2016/09/22.
  */
class OrganisationLocationtest extends FeatureSpec with GivenWhenThen{

  feature("FundersTest") {
    info("FundersTest")
    info("FundersTest")

    scenario("Create New Funding History") {

      Given("Given a Organisation with orgCode,id,name,locationTypeId,code,latitude,longitude,parentId,state:String,date:Date")
      val orgLocation = OrganisationLocation("ZFB0992",new DateTime(),"Gie ","UIO01")
      And(" Given the Repository to Persist a Organisation")
      val LocationRepo = OrganisationLocationRepository
      print(" Object Obtained ", LocationRepo)
      When("When I persist a Organisation Documents")
      LocationRepo.save(orgLocation)
      println(" The Save Executed ")

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(LocationRepo.getLocations("ZFP0992"), 2 minutes)
      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.id == "zw001")
    }
  }

}
