package repositories.people

import conf.connection.DataConnection
import domain.people.UserIdentities
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
import java.util.Date

import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/08/29.
  */
class UserIdentitiesTest extends FeatureSpec with GivenWhenThen{

  
  feature("Create User Identities") {
    info("As a System Admin")
    info("Create User Identities")
    scenario("Create User Identities") {

      Given("Given a User Status Change orgCode:String,email,id,idtype,idValue,issuedDate,expirationDate,countryOfIssue")
      val useridentities = UserIdentities("MMG012","roclet@gmail.com","701","Form Application","5000",new Date(),new Date(),"South Africa")
      And(" Given the Repository to Persist a Users Identities")
      val UserIdentitiesRepo = UserIdentitiesRepository
      print(" Object Obtained ", UserIdentitiesRepo)
      When("When I persist a Users")
      UserIdentitiesRepo.save(useridentities)
      println(" The Save Executed ")

      Then("The Values must validate by User Id")
      val retrievedByUserId = Await.result(UserIdentitiesRepo.getUserIdentitiesByorgCode("MMG012"), 2 minutes)
      And("The Values must validate by Race Id")
      val retrievedById = Await.result(UserIdentitiesRepo.getUserIdentitiesByemail("MMG012","roclet@gmail.com"), 2 minutes)

      And(" Assertion is ")
//      assert(retrievedById.get.id == "701")
    }
  }


}
