package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserStatusChange
import org.joda.time.DateTime

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/16.
  */
class UserStatusChangeRepository extends CassandraTable[UserStatusChangeRepository,UserStatusChange]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object sessionId extends StringColumn(this)
  object id extends StringColumn(this)
  object date extends DateTimeColumn(this) with ClusteringOrder[DateTime] with Descending
  object changedBy extends StringColumn(this)
  object description extends StringColumn(this)
  object oldStatus extends StringColumn(this)
  object newStatus extends StringColumn(this)


  override def fromRow(r:Row):UserStatusChange={
    UserStatusChange(
      orgCode(r),
      email(r),
      id(r),
      changedBy(r),
      date(r),
      sessionId(r),
      description(r),
      oldStatus(r),
      newStatus(r)
    )
  }
}

object UserStatusChangeRepository extends UserStatusChangeRepository with RootConnector{

  override lazy val tableName = "userstatuschange"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(userstatuschange:UserStatusChange):Future[ResultSet]={
      insert
         .value(_.orgCode,userstatuschange.orgCode)
         .value(_.email,userstatuschange.email)
         .value(_.changedBy,userstatuschange.changedBy)
         .value(_.date,userstatuschange.date)
         .value(_.sessionId,userstatuschange.sessionId)
         .value(_.description,userstatuschange.description)
         .value(_.oldStatus,userstatuschange.oldStatus)
         .value(_.newStatus,userstatuschange.newStatus)
        .value(_.id,userstatuschange.id)
         .future()
  }
  def getUserStatusChangeorgCode(orgCode:String):Future[Seq[UserStatusChange]]={
      select.where(_.orgCode eqs orgCode)
        .fetchEnumerator() run Iteratee.collect()
  }
  def getAllUserStatusChange(orgCode:String,email:String):Future[Seq[UserStatusChange]]={
      select.where(_.orgCode eqs orgCode)
        .and(_.email eqs email)
        .fetchEnumerator() run Iteratee.collect()
  }
}
