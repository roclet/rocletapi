package repositories.syslogs

import com.datastax.driver.core.{ResultSet, Row}
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.syslogs.SystemLogEvents

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/07.
  */
class SystemLogEventsRepository extends CassandraTable[SystemLogEventsRepository, SystemLogEvents] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object id extends StringColumn(this) with PrimaryKey[String]

  object eventName extends StringColumn(this)

  object eventType extends StringColumn(this)

  object message extends StringColumn(this)

  object date extends DateTimeColumn(this)

  override def fromRow(row: Row): SystemLogEvents = {
    SystemLogEvents(
      orgCode(row),
      id(row),
      eventName(row),
      eventType(row),
      message(row),
      date(row)
    )
  }
}

object SystemLogEventsRepository extends SystemLogEventsRepository with RootConnector {
  override lazy val tableName = "syslogs"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session


  def save(log: SystemLogEvents): Future[ResultSet] = {
    insert
      .value(_.orgCode, log.orgCode)
      .value(_.id, log.id)
      .value(_.eventName, log.eventName)
      .value(_.eventType, log.eventType)
      .value(_.message, log.message)
      .value(_.date, log.date)
      .ttl(86400)
      .future()
  }

  def getOrganisationLogEvent(orgCode: String, id: String): Future[Option[SystemLogEvents]] = {
    select.where(_.id eqs id).one()
  }

  def getOrganisationLogs(orgCode: String): Future[Seq[SystemLogEvents]] = {
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }

}
