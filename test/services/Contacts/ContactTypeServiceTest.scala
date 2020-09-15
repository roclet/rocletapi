package services.Contacts


import domain.contacts.ContactType
import org.joda.time.DateTime
import org.scalatest.FunSuite
import services.contacts.ContactTypeService

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/08.
  */
class ContactTypeServiceTest extends FunSuite{

  test("testsaveContactTypeService"){
    val event = ContactType("124","Work Address")
    val service = Await.result(ContactTypeService.apply.save(event),2.minutes)
    assert(service.isExhausted)
  }
}
