package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.SettingUploads

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/08/20.
  */
class SettingUploadsRepository extends CassandraTable[SettingUploadsRepository,SettingUploads]{

  object orgCode extends StringColumn(this) with PartitionKey[String]
  object accountingSystem extends StringColumn(this)
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


  override def fromRow(r:Row):SettingUploads={
    SettingUploads(
      orgCode(r),
      mappingTypeId(r),
      accountingSystem(r),
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

object SettingUploadsRepository extends SettingUploadsRepository with RootConnector{
  override lazy val tableName = "settinguploads"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(settinguploads:SettingUploads):Future[ResultSet]={
      insert
        .value(_.orgCode,settinguploads.orgCode)
        .value(_.mappingTypeId,settinguploads.mappingTypeId)
        .value(_.accountingSystem,settinguploads.accountingSystem)
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
  def getOrganisationSettingUpload(orgCode:String):Future[Option[SettingUploads]]={
      select.where(_.orgCode eqs orgCode).one()
  }

}
