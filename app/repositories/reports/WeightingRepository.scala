package repositories.reports

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.reports.Weighting

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/12.
  * orgcode: String,
  * kpa: String,
  * Weighting: String
  */
class WeightingRepository extends CassandraTable[WeightingRepository, Weighting] {

  object orgcode extends StringColumn(this) with PartitionKey[String]

  object kpa extends StringColumn(this) with PrimaryKey[String]

  object weighting extends DoubleColumn(this)


  override def fromRow(r: Row): Weighting = {
    Weighting(
      orgcode(r),
      kpa(r),
      weighting(r)
    )
  }
}

object WeightingRepository extends WeightingRepository with RootConnector {
  override lazy val tableName = "weighting"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(weighting: Weighting): Future[ResultSet] = {
    insert
      .value(_.orgcode, weighting.orgcode)
      .value(_.kpa, weighting.kpa)
      .value(_.weighting, weighting.weighting)
      .future()
  }

  def getWeighting(orgCode: String, kpa: String): Future[Option[Weighting]] = {

    select.where(_.orgcode eqs orgCode).and(_.kpa eqs kpa).one()
  }

  def getWeitings(orgcode: String): Future[Seq[Weighting]] = {

    select.where(_.orgcode eqs orgcode).fetchEnumerator() run Iteratee.collect()
  }

}
