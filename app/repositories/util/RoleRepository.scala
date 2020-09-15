package repositories.util

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.{Roles, _}

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/03.
  */
class RoleRepository extends CassandraTable[RoleRepository, Roles] {


  object id extends StringColumn(this) with PartitionKey[String]

  object rolename extends StringColumn(this)

  object privileges extends SetColumn[String](this)

  override def fromRow(r: Row): Roles = {

    Roles(
      id(r),
      rolename(r),
      privileges(r)
    )
  }
}

object RoleRepository extends RoleRepository with RootConnector {
  override lazy val tableName = "role"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(role: Roles): Future[ResultSet] = {
    insert
      .value(_.id, role.id)
      .value(_.rolename, role.rolename)
      .value(_.privileges, role.privileges)
      .future()
  }

  def getById(id: String): Future[Option[Roles]] = {
    select.where(_.id eqs id).one()
  }

  def getAll: Future[Seq[Roles]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteRoles(id: String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}