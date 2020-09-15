package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.OrganisationType

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/25.
  */
class OrganisationTypeRepository extends CassandraTable[OrganisationTypeRepository,OrganisationType]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)

  override def fromRow(r:Row):OrganisationType={
    OrganisationType(
      id(r),
      name(r)
    )
  }

}
object OrganisationTypeRepository extends OrganisationTypeRepository with RootConnector{
  override lazy val tableName = "organisationtype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(organisationtype:OrganisationType): Future[ResultSet] ={
    insert
      .value(_.id,organisationtype.id)
      .value(_.name,organisationtype.name)
      .future()
  }
  def getByOrganisationTypeId(id:String):Future[Option[OrganisationType]]={
    select.where(_.id eqs id).one()
  }
  def getAllOrg():Future[Seq[OrganisationType]]={
    select.fetchEnumerator() run Iteratee.collect()
  }
}
