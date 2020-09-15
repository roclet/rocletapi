package services.address

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import domain.address.LocationType
import services.address.Impl.LocationTypeServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/21.
  */
trait LocationTypeService {
  def save(locationtypes: LocationType): Future[ResultSet]
  def findById(id: String):Future[Option[LocationType]]
  def findAll: Future[Seq[LocationType]]
}

object LocationTypeService{
  def apply: LocationTypeService = new LocationTypeServiceImpl()
}
