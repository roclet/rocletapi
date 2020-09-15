package repositories.subscriptions


import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.subscriptions.Subscriptions

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/17.
  */
class SubscriptionsRepository extends CassandraTable[SubscriptionsRepository,Subscriptions]{
  object id extends StringColumn(this) with PartitionKey[String]
  object subType extends StringColumn(this)
  object decription extends StringColumn(this)
  object cost extends BigDecimalColumn(this)

  override def fromRow(r:Row):Subscriptions={
    Subscriptions(
      id(r),
      subType(r),
      decription(r),
      cost(r)
    )
  }
}

object SubscriptionsRepository extends SubscriptionsRepository with RootConnector{
  override lazy val tableName = "subscriptions"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(subscriptions:Subscriptions):Future[ResultSet]={
      insert
           .value(_.id,subscriptions.id)
           .value(_.subType,subscriptions.subType)
           .value(_.decription,subscriptions.decription)
           .value(_.cost,subscriptions.cost)
           .future()
  }
  def getSubscriptionsid(id:String):Future[Option[Subscriptions]]={
    select.where(_.id eqs id).one()
  }
  def findAll:Future[Seq[Subscriptions]]={
    select.fetchEnumerator() run Iteratee.collect()
  }
}