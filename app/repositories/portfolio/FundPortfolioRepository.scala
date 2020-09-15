package repositories.portfolio

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.portfolio.FundPortfolio

import scala.concurrent.Future


/**
  * Create
  * ted by hashcode on 2016/02/21.
  */

//fundOrgCode: String, smeOrgCode: String
class FundPortfolioRepository extends CassandraTable[FundPortfolioRepository, FundPortfolio] {

  object fundOrgCode extends StringColumn(this) with PartitionKey[String]

  object smeOrgCode extends StringColumn(this) with PrimaryKey[String]

  override def fromRow(r: Row): FundPortfolio = {
    FundPortfolio(
      fundOrgCode(r),
      smeOrgCode(r)
    )
  }
}

object FundPortfolioRepository extends FundPortfolioRepository with RootConnector {
  override lazy val tableName = "fundportfolio"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(fund: FundPortfolio): Future[ResultSet] = {
    insert
      .value(_.smeOrgCode, fund.smeOrgCode)
      .value(_.fundOrgCode, fund.fundOrgCode)
      .future()
  }

  def getSme(fundOrgCode: String, smeOrgCode: String): Future[Option[FundPortfolio]] = {
    select.where(_.fundOrgCode eqs fundOrgCode).and(_.smeOrgCode eqs smeOrgCode).one()
  }

  def getPortfolio(fundOrgCode: String): Future[Seq[FundPortfolio]] = {
    select.where(_.fundOrgCode eqs fundOrgCode).fetchEnumerator() run Iteratee.collect()
  }
}
