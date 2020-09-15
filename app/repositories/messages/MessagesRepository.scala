package repositories.messages

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.messages.Messages

import play.api.cache.redis.Builders.Future
/**
  * Created by kuminga on 2016/08/17.
  */
class MessagesRepository extends CassandraTable[MessagesRepository,Messages]{
  object orgCode extends StringColumn(this) with PartitionKey[String]
  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]
  object id extends StringColumn(this) with PrimaryKey[String]
  object messageType extends StringColumn(this)
  object messageBody extends StringColumn(this)
  object messageSystemResponse extends StringColumn(this)
  object messageStatus extends StringColumn(this)
  override def fromRow(r:Row):Messages={
    Messages(
      orgCode(r),
      id(r),
      messageType(r),
      messageBody(r),
      messageSystemResponse(r),
      messageStatus(r),
      date(r)
    )
  }
}
object MessagesRepository extends MessagesRepository with RootConnector{
  override lazy val tableName = "messages"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(messages:Messages):Future[ResultSet]={
      insert
            .value(_.orgCode,messages.orgCode)
            .value(_.id,messages.id)
            .value(_.messageType,messages.messageType)
            .value(_.messageBody,messages.messageBody)
            .value(_.messageSystemResponse,messages.messageSystemResponse)
            .value(_.messageStatus,messages.messageStatus)
            .value(_.date,messages.date)
           .future()
  }
  def getMessageByorgCode(orgCode:String):Future[Seq[Messages]]={
     select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getMessageDate(orgCode:String,date:DateTime):Future[Seq[Messages]]={
     select.where(_.orgCode eqs orgCode).and(_.date eqs date).fetchEnumerator() run Iteratee.collect()
  }
  def getMessageById(orgCode:String,date:DateTime,id:String):Future[Option[Messages]]={
     select.where(_.orgCode eqs orgCode).and(_.date eqs date).and(_.id eqs id).one()
  }
}
