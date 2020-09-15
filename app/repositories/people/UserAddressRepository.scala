package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserAddress

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/17.
  */
class UserAddressRepository extends CassandraTable[UserAddressRepository,UserAddress]{
    object  orgCode extends StringColumn(this) with PartitionKey[String]
    object email extends StringColumn(this) with PrimaryKey[String]
    object id extends StringColumn(this) with PrimaryKey[String]
    object addressTypeId extends StringColumn(this)
    object details extends MapColumn[String,String](this)

  override def fromRow(r:Row): UserAddress ={
    UserAddress(
      orgCode(r),
      email(r),
      id(r),
      addressTypeId(r),
      details(r)
    )
  }
}

object UserAddressRepository extends UserAddressRepository with RootConnector{
  override lazy val tableName = "useraddress"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(entityaddress:UserAddress):Future[ResultSet]={
      insert
         .value(_.orgCode,entityaddress.orgCode)
        .value(_.email,entityaddress.email)
        .value(_.id,entityaddress.id)
         .value(_.addressTypeId,entityaddress.addressTypeId)
         .value(_.details,entityaddress.details)
         .future()
  }
  def getUserAddressByOrg(orgCode:String):Future[Seq[UserAddress]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getUserAddressByEmail(orgCode:String,email:String):Future[Seq[UserAddress]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getUserAddressById(orgCode:String,email:String,id:String):Future[Option[UserAddress]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.id eqs id).one()
  }

}