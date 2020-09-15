package repositories.contacts

import domain.contacts.ContactType
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}


import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/05.
  */
class ContactTypeTest extends FeatureSpec with GivenWhenThen{

  feature("Entity Address") {
    info("Entity Address")
    info("I want Entity Address")
    scenario("New Entity Address") {

      Given("Given a Address Type with id,name")
      val contacttype = ContactType("614554","House Address")
      And(" Given the Repository to Persist a Organisation")
      val  ContactTypeRepo = ContactTypeRepository
      print(" Object Obtained ", ContactTypeRepo)
      When("When I persist a Organisation Documents")
      ContactTypeRepo.save(contacttype)
      Then("The Values must validate ")
      val addressAll = Await.result(ContactTypeRepo.findAll, 2 minutes)
      Then("The Values must validate ")
      val retrievedaddId = Await.result(ContactTypeRepo.getAddressTypeById("614554"), 2 minutes)
      println(" The Save Executed ")
      val retrievedelete = Await.result(ContactTypeRepo.deleteContactTypeById("614554"), 2 minutes)

      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.subscriptionsId == "SD1034")findAll
    }
  }
}
