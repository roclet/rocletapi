package services.address

import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl._
import domain.address.AddressType
import services.address.Impl.AddressTypeServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/20.
  */
trait AddressTypeService {
  def save(addresstype:AddressType):Future[ResultSet]
  def findById(id: String):Future[Option[AddressType]]
  def findAll:Future[Seq[AddressType]]
}
object AddressTypeService{
  def apply: AddressTypeService = new AddressTypeServiceImpl()
}
