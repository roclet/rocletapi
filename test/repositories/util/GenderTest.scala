package repositories.util


import org.scalatest.{FeatureSpec, GivenWhenThen}
import domain.util.Gender

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/07.
  */
class GenderTest extends FeatureSpec with GivenWhenThen {

  feature("Create Gender") {
    info("Admin Add a gender")
    scenario("Admin add new gender ") {
      Given("Given A id,name")
      val name ="male"
      val id="12"
      Then("Add gender ")
      val gendertext = Gender(id,name)
      val genderRepo = GenderRepository
      genderRepo.save(gendertext)
      Then("Display All ")
      val displayAllGender = Await.result(genderRepo.getAll, 2 minutes)
      displayAllGender.foreach(i => println("Gender=======>",i))
      val displayIdGender = Await.result(genderRepo.getById(id), 2 minutes)
      displayIdGender.foreach(i => println("Gender=======>",i))
      val displayDeleteGender = Await.result(genderRepo.deleteGender(id), 2 minutes)
      println("Delete Gender=======>")
      //And("CODE  "+code)

    }
  }

}
