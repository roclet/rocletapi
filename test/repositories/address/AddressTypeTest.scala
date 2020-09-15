package repositories.address

import domain.address.AddressType
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}


import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/05.
  */
class AddressTypeTest extends FeatureSpec with GivenWhenThen{
    feature("Address Type") {
      info("Address Type")
      info("I want Address Type")
      scenario("New Address Type") {

        Given("Given a Address Type with id:String,name:String")
        val datep=new DateTime()
        val addresstype = AddressType("75757","Postal code")
        And(" Given the Repository to Persist a Organisation")
        val  AddressTypeRepo = AddressTypeRepository
        print(" Object Obtained ", AddressTypeRepo)
        When("When I persist a Organisation Documents")
        AddressTypeRepo.save(addresstype)
        Then("The Values must validate ")
        val addressAll = Await.result(AddressTypeRepo.findAll, 2 minutes)
        Then("The Values must validate ")
        val retrievedaddId = Await.result(AddressTypeRepo.findById("75757"), 2 minutes)
        println(" The Save Executed ")
//        val retrievedelete = Await.result(AddressTypeRepo.deleteById("75757"), 2 minutes)

        And(" Assertion is ")
        //      assert(retrievedOrganisationDoc.get.subscriptionsId == "SD1034")getAll
      }
    }
}
