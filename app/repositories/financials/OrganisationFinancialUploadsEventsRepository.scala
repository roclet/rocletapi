package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.OrganisationFinancialUploadsEvents

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class OrganisationFinancialUploadsEventsRepository extends CassandraTable[OrganisationFinancialUploadsEventsRepository, OrganisationFinancialUploadsEvents] {

  object fileId extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Ascending

  object status extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(r: Row): OrganisationFinancialUploadsEvents = {
    OrganisationFinancialUploadsEvents(
      fileId(r),
      status(r),
      date(r),
      description(r)
    )
  }
}

object OrganisationFinancialUploadsEventsRepository extends OrganisationFinancialUploadsEventsRepository with RootConnector {

  override lazy val tableName = "orgfinuploadsevents"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(orgFinEvents: OrganisationFinancialUploadsEvents): Future[ResultSet] = {
    insert
      .value(_.fileId, orgFinEvents.fileId)
      .value(_.status, orgFinEvents.status)
      .value(_.date, orgFinEvents.date)
      .value(_.description, orgFinEvents.description)
      .future()
  }

  def getUploadsEvent(fileId: String): Future[Seq[OrganisationFinancialUploadsEvents]] = {
    select.where(_.fileId eqs fileId).fetchEnumerator() run Iteratee.collect()
  }

  def getAllUploadedFileStatusEvents:Future[Seq[OrganisationFinancialUploadsEvents]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}
