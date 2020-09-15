package repositories.util

import org.scalatest.{FeatureSpec, GivenWhenThen}
import domain.util.Title

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/07.
  */
class TitleTest extends FeatureSpec with GivenWhenThen{
  feature("Create Title") {
    info("Admin Add a Title")
    scenario("Admin Add a Title") {
      Given("Given A id,name")
      val name ="male"
      val id="12"
      Then("Add Title ")
      val titletext = Title(id,name)
      val TitleRepo = TitleRepository
      TitleRepo.save(titletext)
      Then("Display All ")
      val displayAllTitle = Await.result(TitleRepo.getAll, 2 minutes)
      displayAllTitle.foreach(i => println("Title=======>",i))
      val displayIdTitle = Await.result(TitleRepo.getById(id), 2 minutes)
      displayIdTitle.foreach(i => println("Title=======>",i))
      println("Delete Gender=======>")
      //And("CODE  "+code)

    }
  }
}
