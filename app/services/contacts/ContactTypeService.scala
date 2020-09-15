package services.contacts

import com.datastax.driver.core.ResultSet
import domain.contacts.ContactType
import services.contacts.Impl.ContactTypeServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/08.
  */
trait ContactTypeService {
   def save(contacttype:ContactType):Future[ResultSet]
   def getContactTypeById(id:String):Future[Option[ContactType]]
   def getAllContacts:Future[Seq[ContactType]]
}
object ContactTypeService{
  def apply: ContactTypeService = new ContactTypeServiceImpl()
}