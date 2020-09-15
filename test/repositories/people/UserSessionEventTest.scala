package repositories.people

import domain.people.UserSessionEvent
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}


import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/08/29.
  */
class UserSessionEventTest extends FeatureSpec with GivenWhenThen{

    feature("Create  Tokens Events") {
      info("Save Tokens Events")
      info("So that  user")

      scenario("Tokens Events") {
        Given("Given a Token Events with sessionId,id,eventTime,eventName")

        val tokenevents = UserSessionEvent( "1111", "2222",new DateTime(),"user login")
        And(" Given the Repository to Persist a Tokens Events")
        val UserSessionEventRepo = UserSessionEventRepository
        print(" Object Obtained ", UserSessionEventRepo)
        When("When I persist a Tokens")
        UserSessionEventRepo.save(tokenevents)
        println(" The Save Executed ")

        Then("The Values must validate by Token Event Id ")
        val retrtId = Await.result(UserSessionEventRepo.getUserSessionEventById("1111","2222"), 2 minutes)

        Then("The Values must validate by Token Id ")
        val retrievedUserSessionEventId = Await.result(UserSessionEventRepo.getUserSessionEventBysessionId("1111"), 2 minutes)

        And(" Assertion is ")
        /*assert(retrievedTokensId.get.id== "1111")*/
      }
    }
}