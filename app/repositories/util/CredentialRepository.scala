package repositories.util

import com.datastax.driver.core.Row
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.util.Credential

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/07.
  */
class CredentialRepository extends CassandraTable[CredentialRepository, Credential] {

  object email extends StringColumn(this) with PartitionKey[String]

  object password extends StringColumn(this)

  object orgCode extends StringColumn(this)

  override def fromRow(row: Row): Credential = {
    Credential(
      email(row),
      password(row),
      orgCode(row)
    )
  }
}

object CredentialRepository extends CredentialRepository with RootConnector {
  override lazy val tableName = "credential"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(credential: Credential): Future[ResultSet] = {
    insert
      .value(_.email, credential.email)
      .value(_.password, credential.password)
      .value(_.orgCode, credential.orgCode)
      .future()
  }

  def findemail(email: String): Future[Option[Credential]] = {
    select.where(_.email eqs email).one()
  }
}