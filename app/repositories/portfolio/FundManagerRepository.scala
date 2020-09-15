package repositories.portfolio

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.portfolio.FundManager

import scala.concurrent.Future


/**
  * Crea
  * ted by hashcode on 2016/02/21.
  */

//fundOrgCode:String,email:String,smeOrgCode:String
class FundManagerRepository extends CassandraTable[FundManagerRepository,FundManager]{

  object fundOrgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]
  object smeOrgCode extends StringColumn(this) with PrimaryKey[String]

  override def fromRow(r: Row): FundManager = {
    FundManager(
      fundOrgCode(r),
      email(r),
      smeOrgCode(r)
    )
  }
}

object FundManagerRepository extends FundManagerRepository with RootConnector {
  override lazy val tableName = "fundmanager"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(location: FundManager): Future[ResultSet] = {
    insert
      .value(_.fundOrgCode, location.fundOrgCode)
      .value(_.email, location.email)
      .value(_.smeOrgCode, location.smeOrgCode)
      .future()
  }

  def getSme(fundOrgCode:String, email:String,smeOrgCode:String):Future[Option[FundManager]] = {
    select.where(_.fundOrgCode eqs fundOrgCode)
      .and(_.email eqs email)
      .and(_.smeOrgCode eqs smeOrgCode)
      .one()
  }
  def getSmes(fundOrgCode:String, email:String) : Future[Seq[FundManager]] = {
    select
      .where(_.fundOrgCode eqs fundOrgCode)
      .and(_.email eqs email)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getAllSmes(fundOrgCode:String) : Future[Seq[FundManager]] = {
    select
      .where(_.fundOrgCode eqs fundOrgCode)
      .fetchEnumerator() run Iteratee.collect()
  }
}
