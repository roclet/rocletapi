package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.{UserSession}
import org.joda.time.DateTime
import play.api.cache.redis.Builders.Future

/**
  * Created by kuminga on 2016/08/16.
  */
class UserSessionsRepository extends CassandraTable[UserSessionsRepository, UserSession] {

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object userSessionId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending

  object startTime extends DateTimeColumn(this) with ClusteringOrder[DateTime] with Descending

  object ipaddress extends StringColumn(this)

  object browserSession extends StringColumn(this)

  object status extends StringColumn(this)

  object tokenId extends StringColumn(this)

  override def fromRow(r: Row): UserSession = {
    UserSession(
      orgCode(r),
      email(r),
      userSessionId(r),
      startTime(r),
      ipaddress(r),
      browserSession(r),
      status(r),
      tokenId(r)
    )
  }
}

object UserSessionsRepository extends UserSessionsRepository with RootConnector {
  override lazy val tableName = "usersessions"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(userSession: UserSession): Future[ResultSet] = {
    insert
      .value(_.orgCode, userSession.orgCode)
      .value(_.email, userSession.email)
      .value(_.userSessionId, userSession.userSessionId)
      .value(_.ipaddress, userSession.ipaddress)
      .value(_.startTime, userSession.startTime)
      .value(_.status, userSession.status)
      .value(_.browserSession, userSession.browserSession)
      .value(_.tokenId, userSession.tokenId)
      .future()
  }
  def getUserSessionsByorgCode(orgCode:String):Future[Seq[UserSession]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getUserSessionsByemail(orgCode:String,email: String): Future[Seq[UserSession]] = {
    select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }

  def getUserSessionsById(orgCode:String,email: String, userSessionId:String): Future[Option[UserSession]] = {
    select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.userSessionId eqs userSessionId).one()
  }

}