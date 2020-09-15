package repositories.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util._

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/03.
  */
class GenderRepository extends CassandraTable[GenderRepository, Gender] {

  object id extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  override def fromRow(r: Row): Gender = {

    Gender(
      id(r),
      name(r)
    )
  }
}

object GenderRepository extends GenderRepository with RootConnector {
  override lazy val tableName = "gender"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(gender: Gender): Future[ResultSet] = {
    insert
      .value(_.id, gender.id)
      .value(_.name, gender.name)
      .future()
  }
  def getById(id:String):Future[Option[Gender]]={
      select.where(_.id eqs id).one()
  }
  def getAll:Future[Seq[Gender]]={
    select.fetchEnumerator() run Iteratee.collect()
  }
  def deleteGender(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}