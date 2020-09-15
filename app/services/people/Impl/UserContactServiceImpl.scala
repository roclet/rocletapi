package services.people.Impl

import com.websudos.phantom.dsl._
import domain.people.UserContact
import services.people.UserContactService
import repositories.people.UserContactRepository
import services.Service

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class UserContactServiceImpl extends UserContactService with Service{

  override def createOrupdate(usercontact:UserContact):Future[ResultSet]={
    UserContactRepository.save(usercontact)
  }
  override def getAllUserContactByorgCode(orgCode:String):Future[Seq[UserContact]]={
    UserContactRepository.getAllUserContactByorgCode(orgCode)
  }
  override def getAllUserContactByemail(orgCode:String,email:String):Future[Seq[UserContact]]={
    UserContactRepository.getAllUserContactByemail(orgCode,email)
  }
  override def getAllUserContactByid(orgCode:String,email:String,id:String):Future[Option[UserContact]]={
    UserContactRepository.getAllUserContactByid(orgCode,email,id)
  }
}
