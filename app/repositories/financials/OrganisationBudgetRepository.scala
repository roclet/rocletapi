package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.OrganisationBudget

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/01/10.
  * orgCode: String,
  * year: Int,
  * itemCode: String,
  * itemDescription: String,
  * m1: Float,
  * m2: Float,
  * m3: Float,
  * m4: Float,
  * m5: Float,
  * m6: Float,
  * m7: Float,
  * m8: Float,
  * m9: Float,
  * m10: Float,
  * m11: Float,
  * m12: Float
  */
class OrganisationBudgetRepository extends CassandraTable[OrganisationBudgetRepository, OrganisationBudget] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object year extends IntColumn(this) with PrimaryKey[Int]

  object itemCode extends StringColumn(this) with PrimaryKey[String]

  object itemDescription extends StringColumn(this)

  object status extends StringColumn(this)

  object m1 extends FloatColumn(this)

  object m2 extends FloatColumn(this)

  object m3 extends FloatColumn(this)

  object m4 extends FloatColumn(this)

  object m5 extends FloatColumn(this)

  object m6 extends FloatColumn(this)

  object m7 extends FloatColumn(this)

  object m8 extends FloatColumn(this)

  object m9 extends FloatColumn(this)

  object m10 extends FloatColumn(this)

  object m11 extends FloatColumn(this)

  object m12 extends FloatColumn(this)


  // Transaction Type

  override def fromRow(r: Row): OrganisationBudget = {
    OrganisationBudget(
      orgCode(r),
      year(r),
      itemCode(r),
      itemDescription(r),
      status(r),
      m1(r),
      m2(r),
      m3(r),
      m4(r),
      m5(r),
      m6(r),
      m7(r),
      m8(r),
      m9(r),
      m10(r),
      m11(r),
      m12(r)
    )
  }
}

object OrganisationBudgetRepository extends OrganisationBudgetRepository with RootConnector {
  override lazy val tableName = "orgbudget"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(budget: OrganisationBudget): Future[ResultSet] = {
    insert
      .value(_.orgCode, budget.orgCode)
      .value(_.year, budget.year)
      .value(_.itemCode, budget.itemCode)
      .value(_.itemDescription, budget.itemDescription)
      .value(_.status, budget.status)
      .value(_.m1, budget.m1)
      .value(_.m2, budget.m2)
      .value(_.m3, budget.m3)
      .value(_.m4, budget.m4)
      .value(_.m5, budget.m5)
      .value(_.m6, budget.m6)
      .value(_.m7, budget.m7)
      .value(_.m8, budget.m8)
      .value(_.m9, budget.m9)
      .value(_.m10, budget.m10)
      .value(_.m11, budget.m11)
      .value(_.m12, budget.m12)
      .future()
  }

  def getOrganisationBudgets(orgCode: String): Future[Seq[OrganisationBudget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getOrganisationBudgetByYear(orgCode: String, year: Int): Future[Seq[OrganisationBudget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getOrganisationBudgetItemByYear(orgCode: String, year: Int, itemCode: String): Future[Option[OrganisationBudget]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .and(_.itemCode eqs itemCode)
      .one()
  }

}
