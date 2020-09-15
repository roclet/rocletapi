package services.address.Impl

import com.datastax.driver.core.ResultSet
import repositories.address.LocationTypeRepository
import services.Service
import services.address.LocationTypeService
import domain.address.{AddressType, LocationType}

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/21.
  */
class LocationTypeServiceImpl extends LocationTypeService with Service{
   override def save(locationtypes: LocationType): Future[ResultSet]={
     LocationTypeRepository.save(locationtypes)
   }
   override def findById(id: String):Future[Option[LocationType]]={
     LocationTypeRepository.findById(id)
   }
  override def findAll: Future[Seq[LocationType]]={
    LocationTypeRepository.findAll
  }
}
