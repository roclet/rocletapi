package repositories.reports

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.reports.FinancialRatio

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/12.
  * kpa: String,
  * kpi: String,
  * target: Float,
  * uom: Float,
  * importance: String,
  * ftype: String,
  * orgcode: String
  */
class FinancialRatioRepository extends CassandraTable[FinancialRatioRepository, FinancialRatio] {

  object orgcode extends StringColumn(this) with PartitionKey[String]

  object kpa extends StringColumn(this) with PrimaryKey[String]

  object kpi extends StringColumn(this) with PrimaryKey[String]

  object target extends DoubleColumn(this)

  object uom extends StringColumn(this)

  object importance extends StringColumn(this)

  object ftype extends StringColumn(this)


  //  email:String,smeOrgCode:String,date:DateTime,status:String

  override def fromRow(r: Row): FinancialRatio = {
    FinancialRatio(
      orgcode(r),
      kpa(r),
      kpi(r),
      target(r),
      uom(r),
      importance(r),
      ftype(r)
    )
  }
}

object FinancialRatioRepository extends FinancialRatioRepository with RootConnector {
  override lazy val tableName = "financialratios"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(ratios: FinancialRatio): Future[ResultSet] = {
    insert
      .value(_.orgcode, ratios.orgcode)
      .value(_.kpa, ratios.kpa)
      .value(_.kpi, ratios.kpi)
      .value(_.target, ratios.target)
      .value(_.uom, ratios.uom)
      .value(_.importance, ratios.importance)
      .value(_.ftype, ratios.ftype)
      .future()
  }

  def getFinancialRatioKpi(orgCode: String, kpa: String,kpi:String): Future[Option[FinancialRatio]] = {

    select.where(_.orgcode eqs orgCode).and(_.kpa eqs kpa).and(_.kpi eqs kpi).one()
  }

  def getFinancialRatioKpa(orgcode: String, kpa:String): Future[Seq[FinancialRatio]] = {

    select.where(_.orgcode eqs orgcode).fetchEnumerator() run Iteratee.collect()
  }

  def getFinancialRatios(orgcode: String): Future[Seq[FinancialRatio]] = {

    select.where(_.orgcode eqs orgcode).fetchEnumerator() run Iteratee.collect()
  }



}
