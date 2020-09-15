package repositories.util

import org.scalatest.{FeatureSpec, GivenWhenThen}
import domain.util.Roles

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by kuminga on 2016/09/07.
  */
class RolesTest extends FeatureSpec with GivenWhenThen{


  feature("Create role") {
    info("Admin Add a gender")
    scenario("Admin add new gender ") {
      Given("Given A id,rolename,privileges")
      val name ="male"
      val id="12"
      val privileges=Set("admin", "user")
      Then("Add gender ")
      val roletext = Roles(id,name,privileges)
      val roleRepo = RoleRepository
      roleRepo.save(roletext)
      Then("Display All ")
      val displayAllroles= Await.result(roleRepo.getAll, 2 minutes)
      displayAllroles.foreach(i => println("Roles=======>",i))
      val displayIdRoles = Await.result(roleRepo.getById(id), 2 minutes)
      displayIdRoles.foreach(i => println("Roles=======>",i))
      val displayDeleteRoles= Await.result(roleRepo.deleteRoles(id), 2 minutes)
      println("Delete Roles=======>")
      //And("CODE  "+code)

    }
  }

}
