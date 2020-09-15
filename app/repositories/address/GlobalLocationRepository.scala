package repositories.address

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.address.GlobalLocation
import domain.organisation.OrganisationLocation

import scala.concurrent.Future


/**
  * Crea
  * ted by hashcode on 2016/02/21.
  */


class GlobalLocationRepository extends CassandraTable[GlobalLocationRepository,GlobalLocation]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  object locationTypeId extends StringColumn(this)
  object code extends StringColumn(this)

  object latitude extends StringColumn(this)
  object longitude extends StringColumn(this)
  object parentId extends StringColumn(this)

  object state extends StringColumn(this)
  object date extends DateColumn(this)

  override def fromRow(r: Row): GlobalLocation = {
    GlobalLocation(
      id(r),
      name(r),
      locationTypeId(r),
      code(r),
      latitude(r),
      longitude(r),
      parentId(r),
      state(r),
      date(r)
    )
  }
}

object GlobalLocationRepository extends GlobalLocationRepository with RootConnector {
  override lazy val tableName = "globallocations"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(location: GlobalLocation): Future[ResultSet] = {
    insert
      .value(_.id, location.id)
      .value(_.name, location.name)
      .value(_.locationTypeId, location.locationTypeId)
      .value(_.code, location.code)
      .value(_.latitude, location.latitude)
      .value(_.longitude, location.longitude)
      .value(_.parentId, location.parentId)
      .value(_.state, location.state)
      .value(_.date, location.date)
      .future()
  }
  def getLocationById(id: String):Future[Option[GlobalLocation]] = {
    select.where(_.id eqs id).one()
  }
  def getAllLocations: Future[Seq[GlobalLocation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }
}
