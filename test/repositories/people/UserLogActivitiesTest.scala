package repositories.people

import conf.connection.DataConnection
import domain.people.UserLogActivities
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
import java.util.Date

import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/08/29.
  */
class UserLogActivitiesTest extends FeatureSpec with GivenWhenThen{

  feature("Create User Log Activities") {
    info("As a System Admin")
    info("Create User Log Activities")
    scenario("Create User Log Activities") {


      Given("Given a User Log ActivitiesTest orgCode:String,email,Id,sessionId,details,date,description")
      val userlogactivities = UserLogActivities("GG0129","roclet@gmail.com","01","8012","Login Report",new DateTime(),"I want The report finance")
      And(" Given the Repository to Persist a Users Log Activities")
      val UserLogActivitiesRepo = UserLogActivitiesRepository
      print(" Object Obtained ", UserLogActivitiesRepo)
      When("When I persist a Users")
      UserLogActivitiesRepo.save(userlogactivities)
      println(" The Save Executed ")

      Then("The Values must validate by User Id")
      val retrievedByUserId = Await.result(UserLogActivitiesRepo.getUserLogActivitiesByemail("GG0129","roclet@gmail.com"), 2 minutes)
      /*And("The Values must validate by Race Id")
      val retrievedById = Await.result(UserLogActivitiesRepo("01","701"), 2 minutes)

      And(" Assertion is ")
      assert(retrievedById.get.id == "701")*/
    }
  }
}
