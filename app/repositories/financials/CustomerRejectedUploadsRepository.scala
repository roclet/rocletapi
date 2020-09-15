package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.CustomerRejectedUploads

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/17.
  */
class CustomerRejectedUploadsRepository extends CassandraTable[CustomerRejectedUploadsRepository, CustomerRejectedUploads] {

  object orgCode extends StringColumn(this) with PartitionKey[String]

  object year extends IntColumn(this) with PrimaryKey[Int]

  object month extends IntColumn(this) with PrimaryKey[Int]
  object accountingCode extends StringColumn(this) with PrimaryKey[String]
  object fileId extends StringColumn(this)

  object entryCategory extends StringColumn(this)

  object reference extends OptionalStringColumn(this)

  object date extends DateTimeColumn(this)

  object day extends OptionalIntColumn(this)

  object accountingSystem extends StringColumn(this)

  object debitValue extends BigDecimalColumn(this)

  object creditValue extends BigDecimalColumn(this)

  object entrySubCategory extends StringColumn(this)

  object entryDescription extends StringColumn(this)

  object csvStringInput extends StringColumn(this)

  object mappingCode extends OptionalStringColumn(this)

  object txnType extends StringColumn(this)  // Transaction Type

  override def fromRow(r: Row): CustomerRejectedUploads = {
    CustomerRejectedUploads(
      orgCode(r),
      reference(r),
      date(r),
      accountingCode(r),
      fileId(r),
      year(r),
      month(r),
      day(r),
      accountingSystem(r),
      debitValue(r),
      creditValue(r),
      entryCategory(r),
      entrySubCategory(r),
      entryDescription(r),
      txnType(r),
      csvStringInput(r),
      mappingCode(r)
    )
  }
}

object CustomerRejectedUploadsRepository extends CustomerRejectedUploadsRepository with RootConnector {
  override lazy val tableName = "rejectedcustuploads"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(customeruploads: CustomerRejectedUploads): Future[ResultSet] = {
    insert
      .value(_.orgCode, customeruploads.orgCode)
      .value(_.reference, customeruploads.reference)
      .value(_.date, customeruploads.date)
      .value(_.accountingCode, customeruploads.accountingCode)
      .value(_.fileId,customeruploads.fileId)
      .value(_.year, customeruploads.year)
      .value(_.month, customeruploads.month)
      .value(_.day, customeruploads.day)
      .value(_.accountingSystem, customeruploads.accountingSystem)
      .value(_.debitValue, customeruploads.debitValue)
      .value(_.entryCategory, customeruploads.entryCategory)
      .value(_.entrySubCategory, customeruploads.entrySubCategory)
      .value(_.entryDescription, customeruploads.entryDescription)
      .value(_.creditValue, customeruploads.creditValue)
      .value(_.csvStringInput, customeruploads.csvStringInput)
      .value(_.txnType, customeruploads.txnType)
      .value(_.mappingCode, customeruploads.mappingCode)
      .future()
  }

  def getUploadByOrganisation(orgCode: String): Future[Seq[CustomerRejectedUploads]] = {
    select
      .where(_.orgCode eqs orgCode)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getUploadsYear(orgCode: String, year: Int): Future[Seq[CustomerRejectedUploads]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getUploadsByMonth(orgCode: String, year: Int, month: Int): Future[Seq[CustomerRejectedUploads]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .and(_.month eqs month)
      .fetchEnumerator() run Iteratee.collect()
  }

  def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String)
  : Future[Option[CustomerRejectedUploads]] = {
    select
      .where(_.orgCode eqs orgCode)
      .and(_.year eqs year)
      .and(_.month eqs month)
      .and(_.accountingCode eqs accountingCode)
      .one()
  }

}
