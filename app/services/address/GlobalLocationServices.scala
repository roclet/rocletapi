package services.address

import com.websudos.phantom.dsl._
import domain.address.{Cordinates, GlobalLocation}
import services.address.Impl.GlobalLocationServicesImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/03.
  */
trait GlobalLocationServices {
  def getCordinates(request: String):Future[Cordinates]

  def saveOrUpdate(location: GlobalLocation): Future[ResultSet]
  def getLocationById(id: String):Future[Option[GlobalLocation]]
  def getAllLocations : Future[Seq[GlobalLocation]]
}
object GlobalLocationServices{

  def apply(): GlobalLocationServices = new GlobalLocationServicesImpl()
}

