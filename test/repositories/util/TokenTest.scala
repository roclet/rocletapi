package repositories.util


import org.scalatest.{FeatureSpec, GivenWhenThen}
import domain.util.Token

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/07.
  */
class TokenTest extends FeatureSpec with GivenWhenThen{
  feature("Create Token") {
    info("Admin Add a Token")
    scenario("Admin Add a Token") {
      Given("Given A id,tokenValue")
      val tokenValue ="12982"
      val id="122"
      Then("Add Token ")
      val titletext = Token(id,tokenValue)
      val TokenRepo = TokenRepository
      TokenRepo.save(titletext)
      Then("Display All ")
      val displayTokenId = Await.result(TokenRepo.getTokenById(id), 2 minutes)
      displayTokenId.foreach(i => println("Title=======>",i))
      val displayId = Await.result(TokenRepo.deleteToken(id), 2 minutes)
      println("Delete Gender=======>")
      //And("CODE  "+code)

    }
  }
}
