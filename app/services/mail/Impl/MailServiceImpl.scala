package services.mail.Impl

import com.datastax.driver.core.ResultSet
import domain.util.Mail
import repositories.util.MailRepository
import services.Service
import services.mail.MailService

import scala.concurrent.Future
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/07.
  */
class MailServiceImpl extends MailService with Service{
  override def saveOrUpdate(entity: Mail): Future[ResultSet]  = {
    MailRepository.save(entity)
  }

  override def get(orgId: String, id: String): Future[Option[Mail]] = {
    MailRepository.findById(orgId, id)
  }

  override def getAll(orgId: String): Future[Seq[Mail]] = {
    MailRepository.findAll(orgId)
  }

  override def getMailer(orgId: String):Future[Mail] = getAll(orgId) map (
    result => {
      result.toVector( new Random().nextInt(result.size))
    })
}
