package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.dsl._
import conf.connection.DataConnection
import domain.financials.FinanceStatementCategoryCodeMapping

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/08/26.
  */
class FinanceStatementCategoryCodeMappingRepository extends CassandraTable[FinanceStatementCategoryCodeMappingRepository, FinanceStatementCategoryCodeMapping] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object financetype extends StringColumn(this) with PrimaryKey[String]

  object category extends StringColumn(this) with PrimaryKey[String]

  object subcategory extends StringColumn(this) with PrimaryKey[String]

  object id extends StringColumn(this)

  object startCode extends LongColumn(this)

  object endCode extends LongColumn(this)

  object sessionId extends StringColumn(this)

  object date extends DateColumn(this)

  override def fromRow(r: Row): FinanceStatementCategoryCodeMapping = {
    FinanceStatementCategoryCodeMapping(
      orgCode(r),
      id(r),
      financetype(r),
      category(r),
      subcategory(r),
      startCode(r),
      endCode(r),
      sessionId(r),
      date(r)
    )
  }
}

object FinanceStatementCategoryCodeMappingRepository extends FinanceStatementCategoryCodeMappingRepository with RootConnector {
  override lazy val tableName = "financestatementcategorycodemapping"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(financestatementcategory: FinanceStatementCategoryCodeMapping): Future[ResultSet] = {
    insert
      .value(_.orgCode, financestatementcategory.orgCode)
      .value(_.id, financestatementcategory.id)
      .value(_.financetype, financestatementcategory.financetype)
      .value(_.category, financestatementcategory.category)
      .value(_.subcategory, financestatementcategory.subcategory)
      .value(_.startCode, financestatementcategory.startCode)
      .value(_.endCode, financestatementcategory.endCode)
      .value(_.sessionId, financestatementcategory.sessionId)
      .value(_.date, financestatementcategory.date)
      .future()
  }



  def getOrgCategories(orgCode: String): Future[Seq[FinanceStatementCategoryCodeMapping]] = {
    select
      .where(_.orgCode eqs orgCode)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getCategoriesByFinType(orgCode: String, financetype: String): Future[Seq[FinanceStatementCategoryCodeMapping]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.financetype eqs financetype)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getSubCategories(orgCode: String, financetype: String,category:String): Future[Seq[FinanceStatementCategoryCodeMapping]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.financetype eqs financetype)
      .and(_.category eqs category)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getSubCategory(orgCode: String, financetype: String,category:String, subcategory:String): Future[Option[FinanceStatementCategoryCodeMapping]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.financetype eqs financetype)
      .and(_.category eqs category)
      .and(_.subcategory eqs subcategory)
      .one()
  }




}
