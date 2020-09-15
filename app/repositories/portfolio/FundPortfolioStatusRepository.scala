package repositories.portfolio

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.portfolio.FundPortfolioStatus

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */
class FundPortfolioStatusRepository extends CassandraTable[FundPortfolioStatusRepository, FundPortfolioStatus] {

  object fundOrgCode extends StringColumn(this) with PartitionKey[String]

  object smeOrgCode extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object status extends StringColumn(this)

  //  email:String,smeOrgCode:String,date:DateTime,status:String

  override def fromRow(r: Row): FundPortfolioStatus = {
    FundPortfolioStatus(
      fundOrgCode(r),
      smeOrgCode(r),
      date(r),
      status(r)
    )
  }
}

object FundPortfolioStatusRepository extends FundPortfolioStatusRepository with RootConnector {
  override lazy val tableName = "fundportfoliostatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(status: FundPortfolioStatus): Future[ResultSet] = {
    insert
      .value(_.fundOrgCode, status.fundOrgCode)
      .value(_.date, status.date)
      .value(_.smeOrgCode, status.smeOrgCode)
      .value(_.status, status.status)
      .future()
  }

  def getStatus(fundOrgCode: String, smeOrgCode: String): Future[Seq[FundPortfolioStatus]] = {
    select.where(_.fundOrgCode eqs fundOrgCode).and(_.smeOrgCode eqs smeOrgCode).fetchEnumerator() run Iteratee.collect()
  }
}
