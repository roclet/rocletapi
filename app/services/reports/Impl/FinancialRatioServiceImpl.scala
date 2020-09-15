package services.reports.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.reports.FinancialRatio
import repositories.reports.FinancialRatioRepository
import services.Service
import services.reports.FinancialRatioService

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/12.
  */
class FinancialRatioServiceImpl extends FinancialRatioService with Service{
  override def save(ratios: FinancialRatio): Future[ResultSet] = {
    FinancialRatioRepository.save(ratios)
  }

  override def getFinancialRatioKpi(orgCode: String, kpa: String, kpi: String): Future[Option[FinancialRatio]] = {
    FinancialRatioRepository.getFinancialRatioKpi(orgCode,kpa, kpi)
  }

  override def getFinancialRatioKpa(orgcode: String, kpa: String): Future[Seq[FinancialRatio]] = {
    FinancialRatioRepository.getFinancialRatioKpa(orgcode,kpa)
  }

  override def getFinancialRatios(orgcode: String): Future[Seq[FinancialRatio]] = {
    FinancialRatioRepository.getFinancialRatios(orgcode)
  }
}
