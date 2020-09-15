package repositories.people

import domain.people.{User, UserRole}
import org.scalatest.{FeatureSpec, GivenWhenThen}
import conf.security.AuthUtil

import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by kuminga on 2016/08/29.
  */
class UserTest extends FeatureSpec with GivenWhenThen{

  val email ="cecile@gmail.com"
  val password =AuthUtil.encode("nicko123")
  val orgCode ="JJO0124"

  feature("Create new User") {

    info("Create new for organisation User")
    scenario("Create new User") {

      Given("Given a User with email,firstname,lastname,middlename,password,userStatus,orgCode")

      val users = User(email,"Mia","makashi","nbokashanga",password,"active",orgCode)
      And(" Given the Repository to Persist a Users")
      val UserRepo = UserRepository
      print(" Object Obtained ", UserRepo)
      When("When I persist a Users")
      UserRepo.save(users)
      println(" The Save Executed ")


      val userole = UserRole(orgCode,email, "T111")
      And(" Given the Repository to Persist a Users Role")
      val UserRoleRepo = UserRoleRepository
      print(" Object Obtained ", UserRoleRepo)

      When("When I persist a Users")
      UserRoleRepo.save(userole)


      Then("The Values must validate ")


      val retrievedUer = Await.result(UserRepo.getUserByEmail(orgCode,email), 2 minutes)
      And(" Assertion is ")
      assert(retrievedUer.get.email == email)

      val retrievedUerole = Await.result(UserRoleRepo.getByUseremail(orgCode,email), 2 minutes)
      And(" Assertion is User Role")
      print(" ============", retrievedUerole)
    }
  }
}
