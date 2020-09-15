package services.mail

import domain.people.User
import services.mail.Impl.MailPasswordServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/02.
  */
trait MailPasswordService {
   def sendMail(user:User):Future[String]
   def resetAccount(user:User):Future[String]
   def mailAdvisor(user:User):Future[String]
}
object MailPasswordService{
   def apply(): MailPasswordService = new MailPasswordServiceImpl()
}
