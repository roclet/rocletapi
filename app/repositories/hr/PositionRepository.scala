package repositories.hr

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.hr.Position

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  * positionId: String, name: String
  */
class PositionRepository extends CassandraTable[PositionRepository, Position] {

  object positionId extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  override def fromRow(r: Row): Position = {
    Position(
      positionId(r),
      name(r)
    )
  }
}

object PositionRepository extends PositionRepository with RootConnector {
  override lazy val tableName = "positions"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(position: Position): Future[ResultSet] = {
    insert
      .value(_.positionId, position.positionId)
      .value(_.name, position.name)
      .future()
  }

  def getPositions: Future[Seq[Position]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getPosition(positionId: String): Future[Option[Position]] = {
    select.where(_.positionId eqs positionId).one()
  }

}