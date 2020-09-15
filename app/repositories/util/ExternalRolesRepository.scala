package repositories.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.ExternalRoles

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/03/04.
  */
class ExternalRolesRepository extends CassandraTable[ExternalRolesRepository, ExternalRoles] {


  object id extends StringColumn(this) with PartitionKey[String]

  object rolename extends StringColumn(this)

  object privileges extends SetColumn[String](this)

  override def fromRow(r: Row): ExternalRoles = {

    ExternalRoles(
      id(r),
      rolename(r),
      privileges(r)
    )
  }
}

object ExternalRolesRepository extends ExternalRolesRepository with RootConnector {
  override lazy val tableName = "externalroles"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(role: ExternalRoles): Future[ResultSet] = {
    insert
      .value(_.id, role.id)
      .value(_.rolename, role.rolename)
      .value(_.privileges, role.privileges)
      .future()
  }

  def getById(id: String): Future[Option[ExternalRoles]] = {
    select.where(_.id eqs id).one()
  }

  def getAll: Future[Seq[ExternalRoles]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteRoles(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
