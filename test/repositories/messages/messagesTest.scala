package repositories.messages



import domain.messages.Messages
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/05.
  */
class messagesTest extends FeatureSpec with GivenWhenThen{

  val datep=new DateTime()

  feature("Message") {
    info("As a System Admin")
    info("I want to Create a new Message")
    scenario("New Organisation Subscriptions Creation") {

      Given("Given a Subscriptions with orgCode,id,messageType,messageBody,messageSystemResponse,messageStatus,date ")

      val messages = Messages("ZM0994", "ZM002","Registration","vertical organisation","Pastel acc","active",datep)

      And(" Given the Repository to Persist a Organisation")
      val  MessagesRepo = MessagesRepository
      print(" Object Obtained ", MessagesRepo)
      When("When I persist a Organisation Documents")
      MessagesRepo.save(messages)
      println(" The Save Executed ")
      val retrievedOrgall = Await.result(MessagesRepo.getMessageByorgCode("ZM0994"), 2 minutes)
      Then("The Values must validate ")
      val retrievegetMessageDate = Await.result(MessagesRepo.getMessageDate("ZM0994",datep), 2 minutes)

      Then("The Values must validate ")
      val retrievedOrganisationDoc = Await.result(MessagesRepo.getMessageById("ZM0994",datep,"ZM002"), 2 minutes)
      And(" Assertion is ")
      //      assert(retrievedOrganisationDoc.get.subscriptionsId == "SD1034")findAll
    }
  }
}
