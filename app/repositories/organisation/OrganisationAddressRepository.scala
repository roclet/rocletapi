package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.OrganisationAddress
import org.joda.time.DateTime

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/15.
  */
class OrganisationAddressRepository extends CassandraTable[OrganisationAddressRepository,OrganisationAddress]{

      object orgCode extends StringColumn(this) with PartitionKey[String]
      object email extends StringColumn(this) with PrimaryKey[String]
      object id extends StringColumn(this) with PrimaryKey[String]
      object locationId extends StringColumn(this)
      object addressId  extends StringColumn(this)
      object details extends MapColumn[String, String](this)
  override def fromRow(r:Row):OrganisationAddress={
    OrganisationAddress(
      orgCode(r),
      email(r),
      id(r),
      locationId(r),
      addressId(r),
      details(r)
    )
  }
}
object OrganisationAddressRepository extends OrganisationAddressRepository with RootConnector{
  override lazy val tableName = "orgaddress"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(organisationaddress:OrganisationAddress):Future[ResultSet]={
     insert
          .value(_.orgCode,organisationaddress.orgCode)
          .value(_.email,organisationaddress.email)
       .value(_.id,organisationaddress.id)
       .value(_.locationId,organisationaddress.locationId)
       .value(_.addressId,organisationaddress.addressId)
       .value(_.details,organisationaddress.details)
      .future()
  }
  def getOrgAddressByorgCode(orgCode:String):Future[Seq[OrganisationAddress]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getOrgAddressByemail(orgCode:String,email:String):Future[Seq[OrganisationAddress]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getOrgAddressById(orgCode:String,email:String,id:String):Future[Option[OrganisationAddress]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.id eqs id).one()
  }
}