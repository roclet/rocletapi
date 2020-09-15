package repositories.util

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Title

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/07.
  */
class TitleRepository extends CassandraTable[TitleRepository,Title]{
    object id extends StringColumn(this) with PartitionKey[String]
    object name extends StringColumn(this)
  override def fromRow(row: Row): Title = {
    Title(
      id(row),
      name(row)
    )
  }
}
object TitleRepository extends TitleRepository with RootConnector{
  override lazy val tableName = "title"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(title:Title):Future[ResultSet]={
    insert
         .value(_.id,title.id)
      .value(_.name,title.name)
       .future()
  }
  def getById(id:String):Future[Option[Title]]={
      select.where(_.id eqs id).one()
  }
  def getAll:Future[Seq[Title]]={
      select.fetchEnumerator() run Iteratee.collect()
  }
  def deleteGender(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
  def truncateGender(): Future[ResultSet] = {
    truncate().future()
  }
}
