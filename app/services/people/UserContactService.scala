package services.people

import com.websudos.phantom.dsl._
import domain.people.UserContact
import services.people.Impl.UserContactServiceImpl
import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/17.
  */
trait UserContactService {
  def createOrupdate(usercontact:UserContact):Future[ResultSet]
  def getAllUserContactByorgCode(orgCode:String):Future[Seq[UserContact]]
  def getAllUserContactByemail(orgCode:String,email:String):Future[Seq[UserContact]]
  def getAllUserContactByid(orgCode:String,email:String,id:String):Future[Option[UserContact]]
}
object UserContactService{
  def apply: UserContactService = new UserContactServiceImpl()
}
