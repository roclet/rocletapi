package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.SettingUploads
import domain.financials.admin.AccountSystems

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/10/15.
  */
class AccountSystemsRepository extends CassandraTable[AccountSystemsRepository,AccountSystems]{

  object accountSystemsId extends StringColumn(this) with PartitionKey[String]
  object accountingSystemName extends StringColumn(this)
  object mappingTypeId extends StringColumn(this)
  object sessionId extends StringColumn(this)
  object dateFormat extends StringColumn(this)
  object codeColumn extends IntColumn(this)
  object descriptionColumn extends  IntColumn(this)
  object debitColumn extends IntColumn(this)
  object creditColumn extends IntColumn(this)
  object startRow extends IntColumn(this)
  object status extends StringColumn(this)
  object dateRow extends IntColumn(this)
  object dateColumn extends IntColumn(this)

  override def fromRow(r:Row):AccountSystems={
    AccountSystems(
      accountSystemsId(r),
      accountingSystemName(r),
      mappingTypeId(r),
      dateFormat(r),
      codeColumn(r),
      descriptionColumn(r),
      debitColumn(r),
      creditColumn(r),
      startRow(r),
      dateRow(r),
      dateColumn(r),
      status(r),
      sessionId(r)
    )
  }

}


object AccountSystemsRepository extends AccountSystemsRepository with RootConnector{
  override lazy val tableName = "accountsystems"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(settinguploads:AccountSystems):Future[ResultSet]={
    insert
      .value(_.accountSystemsId,settinguploads.accountSystemsId)
      .value(_.mappingTypeId,settinguploads.mappingTypeId)
      .value(_.accountingSystemName,settinguploads.accountingSystemName)
      .value(_.dateFormat,settinguploads.dateFormat)
      .value(_.codeColumn,settinguploads.codeColumn)
      .value(_.descriptionColumn,settinguploads.descriptionColumn)
      .value(_.debitColumn,settinguploads.debitColumn)
      .value(_.creditColumn,settinguploads.creditColumn)
      .value(_.startRow,settinguploads.startRow)
      .value(_.dateRow,settinguploads.dateRow)
      .value(_.dateColumn,settinguploads.dateColumn)
      .value(_.status,settinguploads.status)
      .value(_.sessionId,settinguploads.sessionId)
      .future()
  }
  def getAccountingSystemById(accountSystemsId:String):Future[Option[AccountSystems]]={
    select.where(_.accountSystemsId eqs accountSystemsId).one()
  }
  def getAllAccountingSystems:Future[Seq[AccountSystems]]={
    select.fetchEnumerator() run Iteratee.collect()
  }
}
