package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.column.PrimitiveColumn
import com.websudos.phantom.reactivestreams._
import com.websudos.phantom.dsl._
import conf.connection.DataConnection
import domain.financials.admin.CodeMappingSystem

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/10/15.
  */
class CodeMappingSystemRepository extends CassandraTable[CodeMappingSystemRepository,CodeMappingSystem]{

  object accountSystemId extends StringColumn(this) with PartitionKey[String]
  object id extends  StringColumn(this) with PrimaryKey[String]
  object accountSystemName extends StringColumn(this)
  object financialStatementType extends StringColumn(this)
  object category extends StringColumn(this)
  object subcategory extends StringColumn(this)
  object startCode extends IntColumn(this)
  object endCode extends IntColumn(this)
  object sessionId extends StringColumn(this)
  object date extends DateColumn(this)

  override def fromRow(r:Row):CodeMappingSystem={
    CodeMappingSystem(
      accountSystemId(r),
      id(r),
      accountSystemName(r),
      financialStatementType(r),
      category(r),
      subcategory(r),
      startCode(r),
      endCode(r),
      sessionId(r),
      date(r)
    )
  }
}

object CodeMappingSystemRepository extends CodeMappingSystemRepository with RootConnector{
  override lazy val tableName = "codemappingsystem"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(codeMappingsystem:CodeMappingSystem):Future[ResultSet]={
    insert
      .value(_.accountSystemId,codeMappingsystem.accountSystemId)
      .value(_.accountSystemName,codeMappingsystem.accountSystemName)
      .value(_.financialStatementType,codeMappingsystem.financialStatementType)
      .value(_.category,codeMappingsystem.category)
      .value(_.subcategory,codeMappingsystem.subcategory)
      .value(_.startCode,codeMappingsystem.startCode)
      .value(_.endCode,codeMappingsystem.endCode)
      .value(_.sessionId,codeMappingsystem.sessionId)
      .value(_.id,codeMappingsystem.id)
      .value(_.date,codeMappingsystem.date)
      .future()
  }

  def getCodeMappingSystemById(accountSystemId:String, id:String):Future[Option[CodeMappingSystem]]={
    select.where(_.accountSystemId eqs accountSystemId).and(_.id eqs id).one
  }
  def getAllCodemappingSystems(accountSystemId:String):Future[Seq[CodeMappingSystem]]={
    select.where(_.accountSystemId eqs accountSystemId) fetchEnumerator() run Iteratee.collect()
  }
  def findAll:Future[Seq[CodeMappingSystem]]={
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getAllCodemappingSystems:Future[Seq[CodeMappingSystem]]={
    select. fetchEnumerator() run Iteratee.collect()
  }


}
