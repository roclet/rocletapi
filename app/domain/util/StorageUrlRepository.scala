package domain.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection

import scala.concurrent.Future

/**
 * Created by hashcode on 2016/01/05.
 */
class StorageUrlRepository extends CassandraTable[StorageUrlRepository, StorageUrl] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  object url extends StringColumn(this)


  override def fromRow(r: Row): StorageUrl = {
    StorageUrl(
      id(r),
      name(r),
      url(r)
    )
  }
}

object StorageUrlRepository extends StorageUrlRepository with RootConnector {
  override lazy val tableName = "storageurls"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(link: StorageUrl): Future[ResultSet] = {
    insert
      .value(_.id, link.id)
      .value(_.name, link.name)
      .value(_.url, link.url)
      .future()
  }

  def getAllLinks: Future[Seq[StorageUrl]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getLinkById(id: String): Future[Option[StorageUrl]] = {
    select.where(_.id eqs id).one()
  }
}
