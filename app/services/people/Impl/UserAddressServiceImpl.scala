package services.people.Impl

import com.websudos.phantom.dsl._
import conf.security.AuthUtil
import conf.util.Util
import domain.people.UserAddress
import repositories.people.UserAddressRepository
import services.Service
import services.people.UserAddressService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class UserAddressServiceImpl extends UserAddressService with Service{


  override def createOrupdate(entityaddress:UserAddress):Future[ResultSet]={
    UserAddressRepository.save(entityaddress)
  }
  override def findUserAddressByOrg(orgCode:String):Future[Seq[UserAddress]]={
    UserAddressRepository.getUserAddressByOrg(orgCode)
  }
  override def findUserAddressByEmail(orgCode:String,email:String):Future[Seq[UserAddress]]={
    UserAddressRepository.getUserAddressByEmail(orgCode,email)
  }
  override def findUserAddressById(orgCode:String,email:String,id:String):Future[Option[UserAddress]]={
    UserAddressRepository.getUserAddressById(orgCode,email,id)
  }

}
