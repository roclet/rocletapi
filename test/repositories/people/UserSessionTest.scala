package repositories.people


import conf.connection.DataConnection
import domain.people.UserSession
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
import java.util.Date

import org.joda.time
import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/08/29.
  */
class UserSessionTest extends FeatureSpec with GivenWhenThen{

  feature("Create User Sessions") {
    info("As a System Admin")
    info("Create User Sessions")
    scenario("Create User Sessions ") {

      Given("Given a User Sessions orgCode:String,email,userSessionId,startTime,ipaddress,browserSession,status,tokenId")
      val usersessions = UserSession("TT0012","roclet@gmail.com","123",new time.DateTime(),"119.21.21.23","p000","active","1111")
      And(" Given the Repository to Persist a User Sessions")
      val UserSessionsRepo = UserSessionsRepository
      print(" Object Obtained ", UserSessionsRepo)
      When("When I persist User Sessions")
      UserSessionsRepo.save(usersessions)
      println(" The Save Executed ")

      Then("The Values must validate by User Id")
      /*val retrievedByUserId = Await.result(UserSessionsRepo.getUserLogActivitiesByUserId("01"), 2 minutes)
      And("The Values must validate by Race Id")
      val retrievedById = Await.result(UserLogActivitiesRepo("01","701"), 2 minutes)

      And(" Assertion is ")
      assert(retrievedById.get.id == "701")*/
    }
  }
}
