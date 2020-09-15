package repositories.organisation

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.organisation.OrganisationSubscriptions
import org.joda.time.DateTime

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/14.
  */
class OrganisationSubscrRepository extends CassandraTable[OrganisationSubscrRepository,OrganisationSubscriptions]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object subscriptionsId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object id extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object startDate extends DateTimeColumn(this) with ClusteringOrder[DateTime] with Descending
  object endDate extends DateTimeColumn(this)
  object status extends StringColumn(this)

  override def fromRow(r:Row):OrganisationSubscriptions={
    OrganisationSubscriptions(
      id(r),
      orgCode(r),
      subscriptionsId(r),
      startDate(r),
      endDate(r),
      status(r)
    )
  }
}
object OrganisationSubscrRepository extends OrganisationSubscrRepository with RootConnector{

  override lazy val tableName = "organisationsubscriptions"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(organisationsubscr:OrganisationSubscriptions): Future[ResultSet] ={
    insert
      .value(_.orgCode,organisationsubscr.orgCode)
      .value(_.subscriptionsId,organisationsubscr.subscriptionsId)
      .value(_.startDate,organisationsubscr.startDate)
      .value(_.endDate,organisationsubscr.endDate)
      .value(_.id,organisationsubscr.id)
      .value(_.status,organisationsubscr.status)
      .future()

  }
  def getOrganisationsubscription(orgCode: String,subscriptionsId:String): Future[Seq[OrganisationSubscriptions]]={
    select.where(_.orgCode eqs orgCode).and(_.subscriptionsId eqs subscriptionsId).fetchEnumerator() run Iteratee.collect()
  }
  def findAll(orgCode: String): Future[Seq[OrganisationSubscriptions]]={
    select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getOrgSubcriptById(orgCode: String,subscriptionsId:String,id:String):Future[Option[OrganisationSubscriptions]]={
       select.where(_.orgCode eqs orgCode).and(_.subscriptionsId eqs subscriptionsId).and(_.id eqs id).one()
  }
}