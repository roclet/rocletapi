package repositories.portfolio

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.portfolio.AdvisorPortfolio

import scala.concurrent.Future


/**
  * Crea
  * ted by hashcode on 2016/02/21.
  */

//email:String,smeOrgCode:String,date: DateTime,status:String

class AdvisorPortfolioRepository extends CassandraTable[AdvisorPortfolioRepository, AdvisorPortfolio] {

  object email extends StringColumn(this) with PartitionKey[String]

  object smeOrgCode extends StringColumn(this) with PrimaryKey[String]



  override def fromRow(r: Row): AdvisorPortfolio = {
    AdvisorPortfolio(
      email(r),
      smeOrgCode(r)
    )
  }
}

object AdvisorPortfolioRepository extends AdvisorPortfolioRepository with RootConnector {
  override lazy val tableName = "advisorportfolio"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(advisorPortfolio: AdvisorPortfolio): Future[ResultSet] = {
    insert
      .value(_.email, advisorPortfolio.email)
      .value(_.smeOrgCode, advisorPortfolio.smeOrgCode)
      .future()
  }

  def getSme(email: String, smeOrgCode: String): Future[Option[AdvisorPortfolio]] = {
    select.where(_.email eqs email).and(_.smeOrgCode eqs smeOrgCode).one()
  }

  def getPortfolio(email: String): Future[Seq[AdvisorPortfolio]] = {
    select.where(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
}
