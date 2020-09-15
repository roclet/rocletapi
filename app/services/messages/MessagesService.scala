package services.messages

import com.datastax.driver.core.ResultSet
import domain.messages.Messages
import services.messages.Impl.MessagesServiceImpl
import org.joda.time.DateTime
import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/08.
  */
trait MessagesService {
  def save(messages:Messages):Future[ResultSet]
  def getMessageByorgCode(orgCode:String):Future[Seq[Messages]]
  def getMessageDate(orgCode:String,date:DateTime):Future[Seq[Messages]]
  def getMessageById(orgCode:String,date:DateTime,id:String):Future[Option[Messages]]
}
object MessagesService{
  def apply: MessagesService = new MessagesServiceImpl()
}