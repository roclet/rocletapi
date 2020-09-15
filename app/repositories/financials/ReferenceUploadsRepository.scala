package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.ReferenceUploads

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/08/26.
  */
class ReferenceUploadsRepository extends CassandraTable[ReferenceUploadsRepository, ReferenceUploads] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object sessionId extends StringColumn(this) with PrimaryKey[String]

  object referenceId extends StringColumn(this) with PrimaryKey[String]

  object date extends StringColumn(this)

  object login extends StringColumn(this)

  object username extends StringColumn(this)

  object fullname extends StringColumn(this)

  object url extends StringColumn(this)

  object uploadSettingsId extends StringColumn(this)

  override def fromRow(r: Row): ReferenceUploads = {

    ReferenceUploads(
      orgCode(r),
      sessionId(r),
      referenceId(r),
      date(r),
      login(r),
      username(r),
      fullname(r),
      url(r),
      uploadSettingsId(r)
    )
  }
}

object ReferenceUploadsRepository extends ReferenceUploadsRepository with RootConnector {
  override lazy val tableName = "referenceuploads"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(referenceuploads: ReferenceUploads): Future[ResultSet] = {
    insert
      .value(_.orgCode, referenceuploads.orgCode)
      .value(_.date, referenceuploads.date)
      .value(_.referenceId, referenceuploads.referenceId)
      .value(_.sessionId, referenceuploads.sessionId)
      .value(_.login, referenceuploads.login)
      .value(_.username, referenceuploads.username)
      .value(_.fullname, referenceuploads.fullname)
      .value(_.url, referenceuploads.url)
      .value(_.uploadSettingsId, referenceuploads.uploadSettingsId)
      .future()
  }

  def findByorgCode(orgCode: String): Future[Seq[ReferenceUploads]] = {
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }

  def findBysessionId(orgCode: String, sessionId: String): Future[Seq[ReferenceUploads]] = {
    select.where(_.orgCode eqs orgCode).and(_.sessionId eqs sessionId).fetchEnumerator() run Iteratee.collect()
  }

  def findById(orgCode: String, sessionId: String, referenceId: String): Future[Option[ReferenceUploads]] = {
    select.where(_.orgCode eqs orgCode).and(_.sessionId eqs sessionId).and(_.referenceId eqs referenceId).one()
  }
}
