package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.SubsidizeHistory
import org.joda.time.DateTime

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/20.
  */
class SubsidizeHistoryRepository extends CassandraTable[SubsidizeHistoryRepository,SubsidizeHistory]{

  object funderCode extends StringColumn(this) with PartitionKey[String]
  object orgCode extends StringColumn(this) with PrimaryKey[String]
  object date extends DateTimeColumn(this)
  object status extends StringColumn(this)
  override def fromRow(r:Row):SubsidizeHistory={
    SubsidizeHistory(
      funderCode(r),
      orgCode(r),
      date(r),
      status(r)
    )
  }
}

object SubsidizeHistoryRepository extends SubsidizeHistoryRepository with RootConnector{
  override lazy val tableName = "subsidizehistory"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(subsidizehistory:SubsidizeHistory):Future[ResultSet]={
      insert
         .value(_.funderCode,subsidizehistory.funderCode)
        .value(_.orgCode,subsidizehistory.orgCode)
        .value(_.date,subsidizehistory.date)
        .value(_.status,subsidizehistory.status)
        .future()
  }
  def getbyfundeCode(funderCode:String):Future[Seq[SubsidizeHistory]] ={
    select.where(_.funderCode eqs funderCode).fetchEnumerator() run Iteratee.collect()
  }
  def getorgCode(funderCode:String,orgCode:String):Future[Option[SubsidizeHistory]]={
    select.where(_.funderCode eqs funderCode).and(_.orgCode eqs orgCode).one()
  }
}