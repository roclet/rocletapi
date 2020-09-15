package repositories.portfolio


import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.portfolio.FundManagerStatus

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */

//fundOrgCode:String,email:String,smeOrgCode:String,date:DateTime,status:String
class FundManagerStatusRepository extends CassandraTable[FundManagerStatusRepository, FundManagerStatus] {

  object fundOrgCode extends StringColumn(this) with PartitionKey[String]

  object email extends StringColumn(this) with PartitionKey[String]

  object smeOrgCode extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object status extends StringColumn(this)

  //  email:String,smeOrgCode:String,date:DateTime,status:String

  override def fromRow(r: Row): FundManagerStatus = {
    FundManagerStatus(
      fundOrgCode(r),
      email(r),
      smeOrgCode(r),
      date(r),
      status(r)
    )
  }
}

object FundManagerStatusRepository extends FundManagerStatusRepository with RootConnector {
  override lazy val tableName = "fundmanagerstatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(status: FundManagerStatus): Future[ResultSet] = {
    insert
      .value(_.fundOrgCode, status.fundOrgCode)
      .value(_.email, status.email)
      .value(_.smeOrgCode, status.smeOrgCode)
      .value(_.date, status.date)
      .value(_.status, status.status)
      .future()
  }

  def getStatus(fundOrgCode: String, email: String,smeOrgCode: String): Future[Seq[FundManagerStatus]] = {
    select.where(_.fundOrgCode eqs fundOrgCode)
      .and(_.email eqs email)
      .and(_.smeOrgCode eqs smeOrgCode)
      .fetchEnumerator() run Iteratee.collect()
  }
}
