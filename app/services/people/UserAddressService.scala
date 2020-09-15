package services.people

import com.websudos.phantom.dsl._
import domain.people.UserAddress
import services.people.Impl.UserAddressServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
trait UserAddressService {
  def createOrupdate(entityaddress:UserAddress):Future[ResultSet]
  def findUserAddressByOrg(orgCode:String):Future[Seq[UserAddress]]
  def findUserAddressByEmail(orgCode:String,email:String):Future[Seq[UserAddress]]
  def findUserAddressById(orgCode:String,email:String,id:String):Future[Option[UserAddress]]
}
object UserAddressService{
  def apply: UserAddressService = new UserAddressServiceImpl()
}
