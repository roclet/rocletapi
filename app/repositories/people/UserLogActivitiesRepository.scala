package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserLogActivities
import play.api.cache.redis.Builders.Future

/**
  * Created by kuminga on 2016/08/16.
  */
class UserLogActivitiesRepository extends CassandraTable[UserLogActivitiesRepository, UserLogActivities] {

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object sessionId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object date extends DateTimeColumn(this) with ClusteringOrder[DateTime] with Descending

  object details extends StringColumn(this)

  object description extends StringColumn(this)

  override def fromRow(r: Row): UserLogActivities = {
    UserLogActivities(
      orgCode(r),
      email(r),
      id(r),
      sessionId(r),
      details(r),
      date(r),
      description(r)
    )
  }
}

object UserLogActivitiesRepository extends UserLogActivitiesRepository with RootConnector {
  override lazy val tableName = "userlogactivities"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userLogactivities: UserLogActivities): Future[ResultSet] = {
    insert
      .value(_.orgCode, userLogactivities.orgCode)
      .value(_.id, userLogactivities.id)
      .value(_.email, userLogactivities.email)
      .value(_.sessionId, userLogactivities.sessionId)
      .value(_.details, userLogactivities.details)
      .value(_.date, userLogactivities.date)
      .value(_.description, userLogactivities.description)
      .future()
  }
 def getUserLogActivitiesByorgCode(orgCode:String):Future[Seq[UserLogActivities]]={
     select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
 }
  def getUserLogActivitiesByemail(orgCode:String,email: String): Future[Seq[UserLogActivities]] = {
    select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getUserLogActivitiesBySessionId(orgCode:String,email: String, sessionId: String): Future[Seq[UserLogActivities]] = {
    select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.sessionId eqs sessionId).fetchEnumerator() run Iteratee.collect()
  }
  def getUserLogActivitiesById(orgCode:String,email: String, sessionId: String, id: String): Future[Option[UserLogActivities]] = {
    select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.sessionId eqs sessionId).and(_.id eqs id).one()
  }
}