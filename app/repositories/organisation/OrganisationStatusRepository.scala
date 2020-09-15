package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.{MapColumn, PrimitiveColumn}
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.{Organisation, OrganisationStatus}

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/13.
  */
//orgCode: String, date: DateTime,status:String,detail:String
class OrganisationStatusRepository extends CassandraTable[OrganisationStatusRepository, OrganisationStatus] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]

  object status extends StringColumn(this)

  object detail extends StringColumn(this)


  override def fromRow(r: Row): OrganisationStatus = {
    OrganisationStatus(
      orgCode(r),
      date(r),
      status(r),
      detail(r)
    )
  }
}

object OrganisationStatusRepository extends OrganisationStatusRepository with RootConnector {
  override lazy val tableName = "organisationsstatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(status: OrganisationStatus): Future[ResultSet] = {
    insert
      .value(_.orgCode, status.orgCode)
      .value(_.date, status.date)
      .value(_.status, status.status)
      .value(_.detail, status.detail)
      .future()
  }

  def getOrganisationStatus(orgCode: String): Future[Seq[OrganisationStatus]] = {
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }

}
