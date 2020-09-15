package repositories.people
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserDemographics


import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/14.
  */
class UserDemographicsRepository extends CassandraTable[UserDemographicsRepository,UserDemographics]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]
  object raceId extends StringColumn(this)
  object genderId extends StringColumn(this)
  object title extends StringColumn(this)
  object dob extends DateColumn(this)

  override def fromRow(r: Row):UserDemographics={
    UserDemographics(
      orgCode(r),
      email(r),
      raceId(r),
      title(r),
      dob(r),
      genderId(r)
    )
  }
}

object UserDemographicsRepository extends UserDemographicsRepository with RootConnector{
  override lazy val tableName = "userdemographics"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userdemographics:UserDemographics):Future[ResultSet]={
    insert
      .value(_.orgCode,userdemographics.orgCode)
      .value(_.email,userdemographics.email)
      .value(_.raceId,userdemographics.raceId)
      .value(_.genderId,userdemographics.genderId)
      .value(_.title,userdemographics.title)
      .value(_.dob,userdemographics.dob)
      .future()
  }
  def getUserDemographicByOrgCode(orgCode:String):Future[Seq[UserDemographics]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getUserDemographicsByemail(orgCode:String,email:String):Future[Option[UserDemographics]]={
     select.where(_.orgCode eqs orgCode).and(_.email eqs email).one()
  }

}