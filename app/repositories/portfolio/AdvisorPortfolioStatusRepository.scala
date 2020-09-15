package repositories.portfolio

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.portfolio.AdvisorPortfolioStatus

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */
class AdvisorPortfolioStatusRepository extends CassandraTable[AdvisorPortfolioStatusRepository, AdvisorPortfolioStatus] {

  object email extends StringColumn(this) with PartitionKey[String]

  object smeOrgCode extends StringColumn(this) with PartitionKey[String]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime] with ClusteringOrder[DateTime] with Descending

  object status extends StringColumn(this)

//  email:String,smeOrgCode:String,date:DateTime,status:String

  override def fromRow(r: Row): AdvisorPortfolioStatus = {
    AdvisorPortfolioStatus(
      email(r),
      smeOrgCode(r),
      date(r),
      status(r)
    )
  }
}

object AdvisorPortfolioStatusRepository extends AdvisorPortfolioStatusRepository with RootConnector {
  override lazy val tableName = "advisorportfoliostatus"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(advisorPortfolio: AdvisorPortfolioStatus): Future[ResultSet] = {
    insert
      .value(_.email, advisorPortfolio.email)
      .value(_.date, advisorPortfolio.date)
      .value(_.smeOrgCode, advisorPortfolio.smeOrgCode)
      .value(_.status, advisorPortfolio.status)
      .future()
  }

  def getStatus(email: String, smeOrgCode: String): Future[Seq[AdvisorPortfolioStatus]] = {
    select.where(_.email eqs email).and(_.smeOrgCode eqs smeOrgCode).fetchEnumerator() run Iteratee.collect()
  }
}
