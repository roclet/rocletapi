package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.Organisation
import repositories.people.UserRepository

import scala.concurrent.Future



class OrganisationRepository extends CassandraTable[OrganisationRepository, Organisation] {

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object orgTypeId extends StringColumn(this) with PrimaryKey[String]
  object name extends StringColumn(this)

  object status extends StringColumn(this)

  object contactEmail extends StringColumn(this)
  // THis Map line wrongly show error, but ignore it file compiles fine
  object details extends MapColumn[String, String](this)


  override def fromRow(r: Row): Organisation = {
    Organisation(
      orgCode(r),
      name(r),
      orgTypeId(r),
      status(r),
      contactEmail(r),
      details(r)
    )
  }
}

object OrganisationRepository extends OrganisationRepository with RootConnector {
  override lazy val tableName = "organisations"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisation: Organisation): Future[ResultSet] = {
    insert
      .value(_.orgCode, organisation.orgCode)
      .value(_.name, organisation.name)
      .value(_.orgTypeId, organisation.orgTypeId)
      .value(_.status, organisation.status)
      .value(_.contactEmail, organisation.contactEmail)
      .value(_.details, organisation.details)
      .future()
  }

  def getOrganisationByCode(orgCode: String): Future[Option[Organisation]] = {
    select.where(_.orgCode eqs orgCode).one()
  }

  def getAllOrganisations(startValue: Int): Future[Iterator[Organisation]] = {
    select.fetchEnumerator() run Iteratee.slice(startValue, 100)
  }
  def getAllOrganisationsByTypeId(orgCode: String,orgTypeId:String):Future[Seq[Organisation]]={
      select.where(_.orgCode eqs orgCode).and(_.orgTypeId eqs orgTypeId).fetchEnumerator() run Iteratee.collect()
  }
  def deleteByCode(orgCode: String): Future[ResultSet] = {
    delete.where(_.orgCode eqs orgCode).future()
  }

}
