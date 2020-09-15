package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.OrganisationTarget

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/26.
  */
class OrganisationTargetRepository extends CassandraTable[OrganisationTargetRepository,OrganisationTarget]{

      object orgCode extends StringColumn(this) with PartitionKey[String]
      object id extends StringColumn(this) with PrimaryKey[String]
      object description extends StringColumn(this)
      object targetValue extends BigDecimalColumn(this)
      object date extends DateColumn(this)
  override def fromRow(r:Row):OrganisationTarget={
    OrganisationTarget(
      orgCode(r),
      id(r),
      description(r),
      targetValue(r),
      date(r)
    )
  }
}
object OrganisationTargetRepository extends  OrganisationTargetRepository with RootConnector{
  override lazy val tableName = "organisationtarget"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(organisationtarget:OrganisationTarget):Future[ResultSet]={
       insert
             .value(_.orgCode,organisationtarget.orgCode)
             .value(_.id,organisationtarget.id)
             .value(_.description,organisationtarget.description)
             .value(_.targetValue,organisationtarget.targetValue)
             .value(_.date,organisationtarget.date)
             .future()
  }
  def findByorgCode(orgCode:String):Future[Seq[OrganisationTarget]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def findById(orgCode:String,id:String):Future[Option[OrganisationTarget]]={
      select.where(_.orgCode eqs orgCode).and(_.id eqs id).one()
  }
}
