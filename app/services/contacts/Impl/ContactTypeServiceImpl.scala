package services.contacts.Impl

import com.datastax.driver.core.ResultSet
import repositories.contacts.ContactTypeRepository
import services.Service
import services.contacts.ContactTypeService
import domain.contacts.ContactType
import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/08.
  */
class ContactTypeServiceImpl extends ContactTypeService with Service{
    override def save(contacttype:ContactType):Future[ResultSet]={
      ContactTypeRepository.save(contacttype)
    }
    override def getContactTypeById(id:String):Future[Option[ContactType]]={
      ContactTypeRepository.getAddressTypeById(id)
    }
    def getAllContacts:Future[Seq[ContactType]]={
      ContactTypeRepository.findAll
    }
}
