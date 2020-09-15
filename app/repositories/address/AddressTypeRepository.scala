package repositories.address

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.address.AddressType

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/23.
  */
class AddressTypeRepository extends CassandraTable[AddressTypeRepository,AddressType] {

   object id extends StringColumn(this) with PartitionKey[String]
   object name extends StringColumn(this)
  override def fromRow(r:Row):AddressType={
    AddressType(
      id(r),
      name(r)
    )
  }
}
object AddressTypeRepository extends AddressTypeRepository with RootConnector{
  override lazy val tableName = "addresstype"
  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(addresstype:AddressType):Future[ResultSet]={
      insert
            .value(_.id,addresstype.id)
            .value(_.name,addresstype.name)
            .future()
  }
  def findById(id: String):Future[Option[AddressType]]={
      select.where(_.id eqs id).one()
  }
  def findAll:Future[Seq[AddressType]]={
    select.fetchEnumerator() run Iteratee.collect()
  }
}