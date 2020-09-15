package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.Subsidize

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/20.
  */
class SubsidizeRepository extends CassandraTable[SubsidizeRepository,Subsidize]{
  object findCode extends StringColumn(this) with PartitionKey[String]
  object organisation extends StringColumn(this)
  override def fromRow(r:Row):Subsidize={
    Subsidize(
      findCode(r),
      organisation(r)
    )
  }
}

object SubsidizeRepository extends SubsidizeRepository with RootConnector{
  override lazy val tableName = "subsidize"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(subsidize: Subsidize): Future[ResultSet] ={
    insert
      .value(_.findCode, subsidize.findCode)
      .value(_.organisation, subsidize.organisation)
      .future()
  }
  def getfindCode(findCode:String):Future[Seq[Subsidize]]={
      select.where(_.findCode eqs findCode).fetchEnumerator() run Iteratee.collect()
  }
}
