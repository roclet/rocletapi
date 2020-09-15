package repositories.contacts

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.contacts.ContactType

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/17.
  */
class ContactTypeRepository extends CassandraTable[ContactTypeRepository,ContactType]{
    object id extends StringColumn(this) with PartitionKey[String]
    object name extends StringColumn(this)
  override def fromRow(r:Row):ContactType={
    ContactType(
      id(r),
      name(r)
    )
  }
}

object ContactTypeRepository extends ContactTypeRepository with RootConnector{
  override lazy val tableName = "contacttype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(contacttype:ContactType):Future[ResultSet]={
     insert
          .value(_.id,contacttype.id)
          .value(_.name,contacttype.name)
          .future()
  }
  def getAddressTypeById(id:String): Future[Option[ContactType]] ={
      select.where(_.id eqs id).one()
  }
  def findAll:Future[Seq[ContactType]]={
     select.fetchEnumerator() run Iteratee.collect()
  }
  def deleteContactTypeById(id:String):Future[ResultSet]={
    delete.where(_.id eqs id).future()
  }
  
}