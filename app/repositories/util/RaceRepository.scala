package repositories.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Race

import scala.concurrent.Future

sealed class RaceRepository extends CassandraTable[RaceRepository,Race]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object state extends StringColumn(this)
  override def fromRow(r: Row): Race = {
    Race(id(r),name(r),state(r))
  }
}

object RaceRepository extends RaceRepository with RootConnector {
  override lazy val tableName = "race"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(race: Race): Future[ResultSet] = {
    insert
      .value(_.id, race.id)
      .value(_.name, race.name)
      .value(_.state, race.state)
      .future()
  }

  def findById(id: String):Future[Option[Race]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[Race]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
