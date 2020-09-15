package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserIdentities

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/16.
  */
class UserIdentitiesRepository extends CassandraTable[UserIdentitiesRepository,UserIdentities]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]
  object id extends StringColumn(this) with PrimaryKey[String]
  object idtype extends StringColumn(this)
  object idValue extends StringColumn(this)
  object issuedDate extends DateColumn(this)
  object expirationDate extends DateColumn(this)
  object countryOfIssue extends StringColumn(this)

  override def fromRow(r:Row):UserIdentities={

    UserIdentities(
      orgCode(r),
      email(r),
      id(r),
      idtype(r),
      idValue(r),
      issuedDate(r),
      expirationDate(r),
      countryOfIssue(r)
    )
  }

}
object UserIdentitiesRepository extends UserIdentitiesRepository with RootConnector{
  override lazy val tableName = "useridentities"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(useridentities:UserIdentities):Future[ResultSet]={
    insert
         .value(_.orgCode,useridentities.orgCode)
         .value(_.email,useridentities.email)
         .value(_.id,useridentities.id)
         .value(_.idtype,useridentities.idtype)
         .value(_.idValue,useridentities.idValue)
         .value(_.issuedDate,useridentities.issuedDate)
         .value(_.expirationDate,useridentities.expirationDate)
         .value(_.countryOfIssue,useridentities.countryOfIssue)
         .future()
  }
  def getUserIdentitiesByorgCode(orgCode:String):Future[Seq[UserIdentities]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getUserIdentitiesByemail(orgCode:String,email:String):Future[Seq[UserIdentities]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getUserIdentitiesById(orgCode:String,email:String,id:String):Future[Option[UserIdentities]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.id eqs id).one()
  }
}