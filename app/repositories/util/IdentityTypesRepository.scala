package repositories.util

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.IdentityTypes

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/10.
  */
class IdentityTypesRepository extends CassandraTable[IdentityTypesRepository, IdentityTypes] {

  object id extends StringColumn(this) with PartitionKey[String]

  object value extends StringColumn(this)

  override def fromRow(row: Row): IdentityTypes = {
    IdentityTypes(
      id(row),
      value(row)
    )
  }
}

object IdentityTypesRepository extends IdentityTypesRepository with RootConnector {
  override lazy val tableName = "idtypes"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(idtypes: IdentityTypes): Future[ResultSet] = {
    insert
      .value(_.id, idtypes.id)
      .value(_.value, idtypes.value)
      .future()
  }

  def getById(id: String): Future[Option[IdentityTypes]] = {
    select.where(_.id eqs id).one()
  }

  def getAll: Future[Seq[IdentityTypes]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }


}