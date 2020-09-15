package repositories.util

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Keys

import scala.concurrent.Future

/**
 * Created by /**
  * Created by kuminga on 2016/08/29.
  */
 */
sealed class KeysRepository extends CassandraTable[KeysRepository, Keys] {

  object id extends StringColumn(this) with PartitionKey[String]

  object value extends StringColumn(this)

  override def fromRow(row: Row): Keys = {
    Keys(
      id(row),
      value(row)
    )
  }
}

object KeysRepository extends KeysRepository with RootConnector {
  override lazy val tableName = "tokenkeys"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(key: Keys): Future[ResultSet] = {
    insert
      .value(_.id, key.id)
      .value(_.value, key.value)
      .future()
  }
  def getKeyById(id: String): Future[Option[Keys]] = {
    select.where(_.id eqs id).one()
  }
  def getAllkeys: Future[Seq[Keys]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteKey(id:String) : Future[ResultSet]={
    delete.where(_.id eqs id).future()
  }
}