package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.OrganisationFinancialUploads

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class OrganisationFinancialUploadsRepository extends CassandraTable[OrganisationFinancialUploadsRepository,OrganisationFinancialUploads]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object filedId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending
  object fileUlrl extends StringColumn(this)
  object mime extends StringColumn(this)

  override def fromRow(r:Row):OrganisationFinancialUploads={
    OrganisationFinancialUploads(
      orgCode(r),
      filedId(r),
      fileUlrl(r),
      date(r),
      mime(r)
    )
  }
}
object OrganisationFinancialUploadsRepository extends OrganisationFinancialUploadsRepository with RootConnector{
  override lazy val tableName = "orgfinuploads"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(orgfinuploads:OrganisationFinancialUploads):Future[ResultSet]={
    insert
      .value(_.orgCode,orgfinuploads.orgCode)
      .value(_.filedId,orgfinuploads.filedId)
      .value(_.fileUlrl,orgfinuploads.fileUlrl)
      .value(_.date,orgfinuploads.date)
      .value(_.mime,orgfinuploads.mime)
      .future()
  }
  def getOrganisationUploadedFiles(orgCode:String):Future[Seq[OrganisationFinancialUploads]]={
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }

  def getOrganisationUploadedFile(orgCode:String,filedId:String):Future[Option[OrganisationFinancialUploads]]={
    select.where(_.orgCode eqs orgCode).and(_.filedId eqs filedId).one()
  }
//  def truncateOrganisationFinancialUploads(): Future[ResultSet] = {
//    truncate().future()
//  }
}
