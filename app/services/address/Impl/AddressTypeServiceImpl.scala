package services.address.Impl

import com.datastax.driver.core.ResultSet
import repositories.address.AddressTypeRepository
import services.Service
import services.address.AddressTypeService
import domain.address.AddressType
import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/20.
  */
class AddressTypeServiceImpl extends AddressTypeService with Service{
    override def save(addresstype:AddressType):Future[ResultSet]={
      AddressTypeRepository.save(addresstype)
    }
    override def findById(id: String):Future[Option[AddressType]]={
      AddressTypeRepository.findById(id)
    }
    override def findAll:Future[Seq[AddressType]]={
      AddressTypeRepository.findAll
    }
}
