package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.OrganisationContact
import org.joda.time.DateTime

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/15.
  */
class OrganisationContactRepository extends CassandraTable[OrganisationContactRepository,OrganisationContact]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object contactId extends StringColumn(this)
  object details extends MapColumn[String, String](this)

  override def fromRow(r:Row):OrganisationContact={
    OrganisationContact(
      orgCode(r),
      email(r),
      id(r),
      contactId(r),
      details(r)
    )
  }
}
object OrganisationContactRepository extends OrganisationContactRepository with RootConnector{
  override lazy val tableName = "orgcontact"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(organisationcontact:OrganisationContact):Future[ResultSet]={
     insert
         .value(_.orgCode,organisationcontact.orgCode)
         .value(_.email,organisationcontact.email)
         .value(_.id,organisationcontact.id)
         .value(_.contactId,organisationcontact.contactId)
         .value(_.details,organisationcontact.details)
           .future()
  }
  def getOrganisationContactByorgCode(orgCode:String):Future[Seq[OrganisationContact]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationContactByemail(orgCode:String,email:String):Future[Seq[OrganisationContact]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getOrganisationContactById(orgCode:String,email:String,id:String):Future[Option[OrganisationContact]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.id eqs id).one()
  }
}
