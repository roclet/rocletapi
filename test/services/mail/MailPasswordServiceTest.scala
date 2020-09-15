package services.mail

import domain.people.User
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/10/04.
  */
class MailPasswordServiceTest extends FunSuite {

  test("testSendMail") {
    val user = User("john@test.com", "Joh", "Banda", "JB", "test123", "ACTIVE", "MANR-4BJOI")
    val result =  Await.result(MailPasswordService.apply().sendMail(user), 2.minutes)
    println("hello", result)
  }

}
