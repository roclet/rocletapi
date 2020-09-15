package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserContact

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class UserContactRepository extends CassandraTable[UserContactRepository,UserContact]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object contactTypeId extends StringColumn(this)
  object details extends MapColumn[String,String](this)
  override def fromRow(r:Row): UserContact ={
    UserContact(
      orgCode(r),
      email(r),
      id(r),
      contactTypeId(r),
      details(r)
    )
  }
}
object UserContactRepository extends UserContactRepository with RootConnector{
  override lazy val tableName = "usercontact"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(usercontact:UserContact):Future[ResultSet]={
       insert
          .value(_.orgCode,usercontact.orgCode)
         .value(_.email,usercontact.email)
         .value(_.id,usercontact.id)
         .value(_.contactTypeId,usercontact.contactTypeId)
         .value(_.details,usercontact.details)
           .future()
  }
  def getAllUserContactByorgCode(orgCode:String):Future[Seq[UserContact]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getAllUserContactByemail(orgCode:String,email:String):Future[Seq[UserContact]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getAllUserContactByid(orgCode:String,email:String,id:String):Future[Option[UserContact]]={
     select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.id eqs id).one()
  }
}