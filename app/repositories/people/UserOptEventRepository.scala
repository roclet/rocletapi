package repositories.people


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserOptEvent

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/30.
  */
class UserOptEventRepository extends CassandraTable[UserOptEventRepository,UserOptEvent]{

  object userOtpId extends StringColumn(this) with PartitionKey[String]
  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object eventTime extends DateTimeColumn(this)  with ClusteringOrder[DateTime] with Descending
  object eventName extends StringColumn(this)
  override def fromRow(r:Row):UserOptEvent={
    UserOptEvent(
      userOtpId(r),
      id(r),
      eventTime(r),
      eventName(r)
    )
  }
}
object UserOptEventRepository extends UserOptEventRepository with RootConnector{
  override lazy val tableName = "useroptevent"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(useroptEvent:UserOptEvent):Future[ResultSet]={
       insert
            .value(_.userOtpId,useroptEvent.userOtpId)
            .value(_.id,useroptEvent.id)
            .value(_.eventName,useroptEvent.eventName)
            .value(_.eventTime,useroptEvent.eventTime)
            .future()
  }
  def getOtpEventById(userOtpId:String, id:String):Future[Option[UserOptEvent]]={
      select.where(_.userOtpId eqs userOtpId).and(_.id eqs id).one()
  }
  def getByUserOptId(UserOtpId:String):Future[Seq[UserOptEvent]]={
      select.where(_.userOtpId eqs UserOtpId).fetchEnumerator() run Iteratee.collect()
  }

}
