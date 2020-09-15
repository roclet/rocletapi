package services.mail.Impl

import conf.util.mail.{EmailMessage, MailEvents, Mailer, SmtpConfig}
import domain.organisation.Organisation
import domain.people.User
import domain.util.Mail
import services.Service
import services.organisation.OrganisationServices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * Created by hashcode on 2016/09/07.
  */
protected object SendEmailService extends Service {


  def sendMail(message: String, subject: String, user: User, mail: Mail): Future[String] = {
    val orgdanisation = OrganisationServices.apply().getOrganisationByCode(user.orgCode)
    val resultsMessage: Future[String] = orgdanisation map (org => {

      val orgName = getOrganisationname(org)
      val smtp = SmtpConfig(port = mail.port.toInt, host = mail.host, user = mail.key, password = mail.value)

      val msg = "<html>" +
        "<body>" +
        "<h3> <b>COMPANY NAME : </b>" + orgName+ "</h3></p>"+"Dear " + user.firstname + " " + user.lastname + ",<p/>" + message + "</body></html>"

      val emailMessage = EmailMessage(subject = subject, recipient = user.email, from = mail.key, msg, msg, smtp)

      Mailer.send(emailMessage) match {
        case Success(mail) => MailEvents.MAIL_SENT
        case Failure(t) => MailEvents.MAIL_SENT_FAILED
      }
    })

    resultsMessage
  }

  def getOrganisationname(org: Option[Organisation]) = org match {
    case Some(name) => name.name
    case None => ""
  }

}
