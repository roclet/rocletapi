package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.OrganisationDocuments

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/14.
  */
class OrganisationDocRepository extends CassandraTable[OrganisationDocRepository,OrganisationDocuments]{
  object orgCode extends StringColumn(this) with PartitionKey[String]
  object url extends StringColumn(this) with PrimaryKey[String]
  object docType extends StringColumn(this)
  object date extends DateTimeColumn(this)
  object extension extends StringColumn(this)

  override def fromRow(r:Row):OrganisationDocuments={
    OrganisationDocuments(
      orgCode(r),
      url(r),
      docType(r),
      date(r),
      extension(r)
    )

  }
}

object OrganisationDocRepository extends OrganisationDocRepository with RootConnector{
  override lazy val tableName = "organisationsdocuments"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(organisationdoc:OrganisationDocuments):Future[ResultSet]={
    insert
      .value(_.orgCode,organisationdoc.orgCode)
      .value(_.url,organisationdoc.url)
      .value(_.docType,organisationdoc.docType)
      .value(_.date,organisationdoc.date)
      .value(_.extension,organisationdoc.extension)
      .future()
  }

  def findById(orgCode: String,url:String): Future[Option[OrganisationDocuments]]={
    select.where(_.orgCode eqs orgCode).and(_.url eqs url).one()
  }
  def getOrganisationsDocByCode(orgCode: String): Future[Seq[OrganisationDocuments]]={
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def deleteById(orgCode:String,url:String):Future[ResultSet]={
      delete.where(_.orgCode eqs orgCode).and(_.url eqs url).future()
  }
}