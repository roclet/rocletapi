package repositories.people

import conf.connection.DataConnection
import domain.people.UserStatusChange
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/08/29.
  */
class UserStatusChangeTest extends FeatureSpec with GivenWhenThen{

  feature("Change User Satus") {
    info("As a System Admin")
    info("Change User Satus for your organisation")
    scenario("Change User Satus") {

      Given("Given a User Status Change orgCode,email,id,changedBy,date,sessionId,description,oldStatus,newStatus")
      val userstatus = UserStatusChange("FF432","roclet@gmail.com","10","minga@gmail.com",new DateTime(),"p000","review leads","active","desactive")
      And(" Given the Repository to Persist Status Change")
      val UserStatusChangeRepo = UserStatusChangeRepository
      print(" Object Obtained ", UserStatusChangeRepo)
      When("When I persist a Users")
      UserStatusChangeRepo.save(userstatus)
      println(" The Save Executed ")
      Then("The Values must validate by Id")
      val retrievedByUserId = Await.result(UserStatusChangeRepo.getAllUserStatusChange("FF432","roclet@gmail.com"), 2 minutes)

      Then("The Values must validate by Id")
      val retrievedByroleId= Await.result(UserStatusChangeRepo.getUserStatusChangeorgCode("FF432"), 2 minutes)

      And(" Assertion is ")
      assert(retrievedByUserId!=null)
    }
  }
}
