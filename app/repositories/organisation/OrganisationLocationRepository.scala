package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.OrganisationLocation

import scala.concurrent.Future


/**
  * Crea
  * ted by hashcode on 2016/02/21.
  */


class OrganisationLocationRepository extends CassandraTable[OrganisationLocationRepository,OrganisationLocation]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending
  object locationId extends StringColumn(this)
  object event extends StringColumn(this)


  override def fromRow(r: Row): OrganisationLocation = {
    OrganisationLocation(
      orgCode(r),
      date(r),
      locationId(r),
      event(r)
    )
  }
}

object OrganisationLocationRepository extends OrganisationLocationRepository with RootConnector {
  override lazy val tableName = "orglocations"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(location: OrganisationLocation): Future[ResultSet] = {
    insert
      .value(_.orgCode, location.orgCode)
      .value(_.event, location.event)
      .value(_.date, location.date)
      .value(_.locationId, location.locationId)
      .future()
  }

  def getLocationById(orgCode:String, date: DateTime):Future[Option[OrganisationLocation]] = {
    select.where(_.orgCode eqs orgCode).and(_.date eqs date).one()
  }
  def getLocations(orgCode:String) : Future[Seq[OrganisationLocation]] = {
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
}
