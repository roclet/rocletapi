package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.FinancialApprovedStatement

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/19.
  */
class FinancialApprovedStatementRepository extends CassandraTable[FinancialApprovedStatementRepository,FinancialApprovedStatement]{
  object orgCode extends StringColumn(this) with PartitionKey[String]
  object statementType extends StringColumn(this) with PrimaryKey[String]
  object category extends StringColumn(this) with PrimaryKey[String]
  object year extends IntColumn(this)with PrimaryKey[Int]
  object month extends IntColumn(this) with PrimaryKey[Int]
  object description extends StringColumn(this) with PrimaryKey[String]
  object date extends DateColumn(this)
  object subCategory extends StringColumn(this)
  object amount extends BigDecimalColumn(this)
  object dateCreated extends DateTimeColumn(this)
  object id extends StringColumn(this)
  override def fromRow(r:Row):FinancialApprovedStatement={
    FinancialApprovedStatement(
      orgCode(r),
      id(r),
      statementType(r),
      date(r),
      category(r),
      description(r),
      subCategory(r),
      amount(r),
      month(r),
      year(r),
      dateCreated(r)

    )
  }
}
object FinancialApprovedStatementRepository extends FinancialApprovedStatementRepository with RootConnector{
  override lazy val tableName = "astatements"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(statements:FinancialApprovedStatement):Future[ResultSet]={
    insert
      .value(_.orgCode,statements.orgCode)
      .value(_.statementType,statements.statementType)
      .value(_.date,statements.date)
      .value(_.category,statements.category)
      .value(_.description,statements.description)
      .value(_.subCategory,statements.subCategory)
      .value(_.amount,statements.amount)
      .value(_.month,statements.month)
      .value(_.year,statements.year)
      .value(_.dateCreated,statements.dateCreated)
      .value(_.id,statements.id)
      .future()
  }
  def getStatements(orgCode:String):Future[Seq[FinancialApprovedStatement]]={
    select
      .where(_.orgCode eqs orgCode)
      .fetchEnumerator run Iteratee
      .collect()
  }
  def getStatementsByType(orgCode:String, statementType:String):Future[Seq[FinancialApprovedStatement]]={
    select
      .where(_.orgCode eqs orgCode)
      .and(_.statementType eqs statementType)
      .fetchEnumerator run Iteratee
      .collect()
  }

  def getStatementsByCategory(orgCode:String, statementType:String, category:String ):Future[Seq[FinancialApprovedStatement]]={
    select
      .where(_.orgCode eqs orgCode)
      .and(_.statementType eqs statementType)
      .and(_.category eqs category)
      .fetchEnumerator run Iteratee
      .collect()
  }

  def getStatementsByYear(orgCode:String, statementType:String, category:String, year:Int ):Future[Seq[FinancialApprovedStatement]]={
    select
      .where(_.orgCode eqs orgCode)
      .and(_.statementType eqs statementType)
      .and(_.category eqs category)
      .and(_.year eqs year)
      .fetchEnumerator run Iteratee
      .collect()
  }

  def getStatementsByMonth(orgCode:String, statementType:String, category:String, year:Int, month:Int):Future[Seq[FinancialApprovedStatement]]={
    select
      .where(_.orgCode eqs orgCode)
      .and(_.statementType eqs statementType)
      .and(_.category eqs category)
      .and(_.year eqs year)
      .and(_.month eqs month)
      .fetchEnumerator run Iteratee
      .collect()
  }

}
