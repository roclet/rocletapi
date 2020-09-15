package services.messages.Impl

import com.datastax.driver.core.ResultSet
import domain.messages.Messages
import org.joda.time.DateTime
import repositories.messages.MessagesRepository
import services.Service
import services.messages.MessagesService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/08.
  */
class MessagesServiceImpl extends MessagesService with Service{
    override def save(messages:Messages):Future[ResultSet]={
      MessagesRepository.save(messages)
    }
    override def getMessageByorgCode(orgCode:String):Future[Seq[Messages]]={
      MessagesRepository.getMessageByorgCode(orgCode)
    }
    override def getMessageDate(orgCode:String,date:DateTime):Future[Seq[Messages]]={
      MessagesRepository.getMessageDate(orgCode,date)
    }
    override def getMessageById(orgCode:String,date:DateTime,id:String):Future[Option[Messages]]={
      MessagesRepository.getMessageById(orgCode,date,id)
    }
}
