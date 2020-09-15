package services.mail

import java.util.Date

import domain.util.Mail
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/10/04.
  */
class MailServiceTest extends FunSuite {

  test("testSaveOrUpdate") {
    val mail = Mail("MARGINM", "test", "no_reply_no@hashcode.zm", "20Mataba04", "smtp.gmail.com", "587", "ACTIVE", new Date())
    val result = Await.result(MailService.apply().saveOrUpdate(mail), 2.minutes)
    assert(result.isExhausted)
  }

  test("testGetMailer") {
    val result = Await.result(MailService.apply().getMailer("MARGINM"), 2.minutes)
    assert(result.id === "test")
  }

  test("testGet") {
    val result = Await.result(MailService.apply().get("MARGINM", "test"), 2.minutes)

    assert(result.get.id === "test")
  }

  test("testGetAll") {
    val result = Await.result(MailService.apply().getAll("MARGINM"), 2.minutes)

    assert(result.head.id === "test")
  }

}
