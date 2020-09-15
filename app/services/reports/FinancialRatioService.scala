package services.reports

import com.websudos.phantom.dsl.ResultSet
import domain.reports.FinancialRatio
import services.reports.Impl.FinancialRatioServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/12.
  */
trait FinancialRatioService {

  def save(ratios: FinancialRatio): Future[ResultSet]

  def getFinancialRatioKpi(orgCode: String, kpa: String, kpi: String): Future[Option[FinancialRatio]]

  def getFinancialRatioKpa(orgcode: String, kpa: String): Future[Seq[FinancialRatio]]

  def getFinancialRatios(orgcode: String): Future[Seq[FinancialRatio]]

}

object FinancialRatioService{

  def apply: FinancialRatioService = new FinancialRatioServiceImpl()
}
