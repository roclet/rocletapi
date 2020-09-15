package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.{UserSessionEvent}

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/29.
  */
class UserSessionEventRepository extends CassandraTable[UserSessionEventRepository,UserSessionEvent]{
  object sessionId extends StringColumn(this) with PartitionKey[String]
  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object eventTime extends DateTimeColumn(this)  with ClusteringOrder[DateTime] with Descending
  object eventName extends StringColumn(this)
  override def fromRow(r:Row):UserSessionEvent={
    UserSessionEvent(
      sessionId(r),
      id(r),
      eventTime(r),
      eventName(r)
    )
  }
}
object UserSessionEventRepository extends UserSessionEventRepository with RootConnector{
  override lazy val tableName = "usersesionevents"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(tokenevents:UserSessionEvent):Future[ResultSet]={
     insert
          .value(_.sessionId,tokenevents.sessionId)
          .value(_.id,tokenevents.id)
          .value(_.eventTime,tokenevents.eventTime)
          .value(_.eventName,tokenevents.eventName)
          .future()
  }
  def getUserSessionEventBysessionId(sessionId:String):Future[Seq[UserSessionEvent]]={
     select.where(_.sessionId eqs sessionId).fetchEnumerator() run Iteratee.collect()
  }
  def getUserSessionEventById(sessionId:String,id:String):Future[Option[UserSessionEvent]]={
      select.where(_.sessionId eqs sessionId).and(_.id eqs id).one()
  }
}