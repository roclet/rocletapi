package services.mail

import com.datastax.driver.core.ResultSet
import domain.util.Mail
import services.mail.Impl.MailServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/07.
  */
trait MailService {
  def saveOrUpdate(entity: Mail): Future[ResultSet]

  def get(orgId: String, id: String): Future[Option[Mail]]

  def getAll(orgId: String): Future[Seq[Mail]]

  def getMailer(orgId: String):Future[Mail]
}

object MailService {
  def apply(): MailService = new MailServiceImpl()
}
