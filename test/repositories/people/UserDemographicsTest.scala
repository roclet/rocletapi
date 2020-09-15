package repositories.people

import java.util.Date

import conf.connection.DataConnection
import domain.people.UserDemographics
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/08/29.
  */
class UserDemographicsTest extends FeatureSpec with GivenWhenThen{


  feature("Create User Demograohics") {
    info("As a System Admin")
    info("Create User Demograohics")
    scenario("Create User Demograohics") {

      Given("Given a User Demograohics     orgCode: String,  email,raceId,title,dob,genderId")
      val userdemographics = UserDemographics("MM3021","roclet@gmail.com", "12001", "Mr",new Date,"1201")
      And(" Given the Repository to Persist a Users Demographics")
      val UserDemographicsRepo = UserDemographicsRepository
      print(" Object Obtained ", UserDemographicsRepo)
      When("When I persist a Users")
      UserDemographicsRepo.save(userdemographics)
      println(" The Save Executed ")

      Then("The Values must validate by User Id")
      val retrievedByUserId = Await.result(UserDemographicsRepo.getUserDemographicByOrgCode("MM3021"), 2 minutes)
      And("The Values must validate by Race Id")


      /*Then("The Values must validate by Id")
      val retrievedById= Await.result(UserDemographicsRepo.getUserDemographicsById("01","00001","1201"), 2 minutes)

      Then("The Values must validate by Id")
      val retrievedById = Await.result(UserRoleRepo.findById("01","00001","001"), 2 minutes)*/


      And(" Assertion is ")
      //      assert(retrievedById.get.id == "011")
    }
  }
}
