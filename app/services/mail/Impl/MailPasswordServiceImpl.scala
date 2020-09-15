package services.mail.Impl

import java.util.UUID

import conf.security.AuthUtil
import conf.util.{MarginKeys, Util}
import conf.util.mail.MailEvents
import domain.people.User
import domain.syslogs.SystemLogEvents
import org.joda.time.DateTime
import services.Service
import services.mail.{MailPasswordService, MailService}
import services.organisation.OrganisationServices
import services.people.UserService
import services.syslogs.SystemLogEventsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/07.
  */
class MailPasswordServiceImpl extends MailPasswordService with Service {


  def sendMail(user: User): Future[String] = {

    val mailer = MailService.apply.getMailer(MarginKeys.MAILORG)
    val organisation = OrganisationServices.apply().getOrganisationByCode(user.orgCode)



    val result = mailer map (mail => {

      val passwd = AuthUtil.generateRandomPassword()
      val updatePerson = user.copy(password = AuthUtil.encode(passwd))
      UserService.apply.updateUser(updatePerson)
      val subject = "New User " + updatePerson.email + " Margin Mentor Login Credentials"
      val message = "You have been logged as a new user on the Margin Mentor System " +
        "Your Login Details are : <p/>" +
        "Username: " + updatePerson.email + " <br/> " +
        "Password: " + passwd + " <p/>" +
        "<b>PLEASE REMEMBER TO CHANGE YOUR PASSWORD</b><p/>" +
        "To make use of the system, please log on to the Margin Mentor Application Using the following web address: https://ibs.margin-mentor.com"

      SendEmailService.sendMail(message, subject, user, mail) map (result => {
        if (result == MailEvents.MAIL_SENT) {
          val successEvent = SystemLogEvents(
            user.orgCode,
            Util.md5Hash(UUID.randomUUID().toString),
            MailEvents.MAIL,
            MailEvents.MAIL_SENT, MailEvents.MAIL_SENT, new DateTime())
          SystemLogEventsService.apply.save(successEvent)
          MailEvents.MAIL_SENT
        } else {
          val failEvent = SystemLogEvents(
            user.orgCode,
            Util.md5Hash(UUID.randomUUID().toString),
            MailEvents.MAIL,
            MailEvents.MAIL_SENT_FAILED, MailEvents.MAIL_SENT_FAILED, new DateTime())
          SystemLogEventsService.apply.save(failEvent)
          MailEvents.MAIL_SENT_FAILED
        }
      })
    })

    result flatMap  { response => response}
  }

  override def resetAccount(user: User): Future[String] = {
    val mailer = MailService.apply.getMailer(MarginKeys.MAILORG)
    val result = mailer map (mail => {

      val passwd = AuthUtil.generateRandomPassword()
      val updatePerson = user.copy(password = AuthUtil.encode(passwd))
      UserService.apply.updateUser(updatePerson)
      val subject = "Your Reset New Login Credentials "
      val message = "You have been logged as a new user on the Margin Mentor System " +
        "Your Login Details are : <p/>" +
        "Username: " + updatePerson.email + " <br/> " +
        "Password: " + passwd + " <p/>" +
        "You can access the Site  Provided to you By the Provider. <p/> " +
        "<b>PLEASE REMEMBER TO CHANGE YOUR PASSWORD</b><p/>" +
        "To make use of the system, please log on to the Margin Mentor Application Using the following web address: https://ibs.margin-mentor.com"

      SendEmailService.sendMail(message, subject, user, mail) map (result => {
        if (result == MailEvents.MAIL_SENT) {
          val successEvent = SystemLogEvents(
            user.orgCode,
            Util.md5Hash(UUID.randomUUID().toString),
            MailEvents.MAIL,
            MailEvents.MAIL_SENT, MailEvents.MAIL_SENT, new DateTime())
          SystemLogEventsService.apply.save(successEvent)
          MailEvents.MAIL_SENT
        } else {
          val failEvent = SystemLogEvents(
            user.orgCode,
            Util.md5Hash(UUID.randomUUID().toString),
            MailEvents.MAIL,
            MailEvents.MAIL_SENT_FAILED, MailEvents.MAIL_SENT_FAILED, new DateTime())
          SystemLogEventsService.apply.save(failEvent)
          MailEvents.MAIL_SENT_FAILED
        }
      })
    })
    result flatMap  { response => response}
  }

  override def mailAdvisor(user: User): Future[String] = {
    val mailer = MailService.apply.getMailer(MarginKeys.MAILORG)
    val result = mailer map (mail => {

      val subject = "You Have Been Assigned an Company "
      val message = "Dear  " + user.firstname + "," +
        "</p> You have been Assigned a Company " +
        "</p>Please, setup the Company Profile, activate the Company and contact the Business Owner to setup a meeting"

      SendEmailService.sendMail(message, subject, user, mail) map (result => {
        if (result == MailEvents.MAIL_SENT) {
          val successEvent = SystemLogEvents(
            user.orgCode,
            Util.md5Hash(UUID.randomUUID().toString),
            MailEvents.MAIL,
            MailEvents.MAIL_SENT, MailEvents.MAIL_SENT, new DateTime())
          SystemLogEventsService.apply.save(successEvent)
          MailEvents.MAIL_SENT
        } else {
          val failEvent = SystemLogEvents(
            user.orgCode,
            Util.md5Hash(UUID.randomUUID().toString),
            MailEvents.MAIL,
            MailEvents.MAIL_SENT_FAILED, MailEvents.MAIL_SENT_FAILED, new DateTime())
          SystemLogEventsService.apply.save(failEvent)
          MailEvents.MAIL_SENT_FAILED
        }
      })
    })
    result flatMap  { response => response}
  }
}
