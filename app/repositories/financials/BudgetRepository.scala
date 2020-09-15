package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.Budget

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/17.
CREATE TABLE marginmentor.budget (
    orgcode text,
    year int,
    month int,
    item text,
    amount float,
    fullname text,
    month_desc text,
    PRIMARY KEY (orgcode, year, month, item)
  * dec: BigDecimal
  */
class BudgetRepository extends CassandraTable[BudgetRepository, Budget] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object year extends IntColumn(this) with PrimaryKey[Int]

  object month extends IntColumn(this) with PrimaryKey[Int]

  object item extends StringColumn(this) with PrimaryKey[String]

  object amount extends FloatColumn(this)

  object fullname extends StringColumn(this)

  object month_desc extends StringColumn(this)



  // Transaction Type

  override def fromRow(r: Row): Budget = {
    Budget(
      orgCode(r),
      year(r),
      month(r),
      item(r),
      amount(r),
      fullname(r),
      month_desc(r)
    )
  }
}

object BudgetRepository extends BudgetRepository with RootConnector {
  override lazy val tableName = "budget"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(budget: Budget): Future[ResultSet] = {
    insert
      .value(_.orgCode, budget.orgCode)
      .value(_.year, budget.year)
      .value(_.month, budget.month)
      .value(_.item, budget.item)
      .value(_.amount, budget.amount)
      .value(_.fullname, budget.fullname)
      .value(_.month_desc, budget.month_desc)
      .future()
  }

  def getOrganisationBudgets(orgCode: String): Future[Seq[Budget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getOrganisationBudgetByYear(orgCode: String, year: Int): Future[Seq[Budget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getItemsBudgetByMonth(orgCode: String, year: Int,month:Int): Future[Seq[Budget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .and(_.month eqs month)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getOrganisationBudgetItemByMonth(orgCode: String, year: Int, month:Int,item:String): Future[Option[Budget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .and(_.month eqs month)
      .and(_.item eqs item)
      .one()
  }

}
