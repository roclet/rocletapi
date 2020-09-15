package services.reports

import domain.reports.KpiIndicator
import services.reports.Impl.KpaServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/13.
  */
trait KpaService {

  def indicator(target: String, uom: String, importance: String, ftype: String): Map[String, String]

  def profitabilityKpi(kpi: Map[String, Map[String, String]]): Map[String, Map[String, String]]

  def profitabilityKpa(weighting: String, kpi: Map[String, Map[String, String]]): KpiIndicator

  def getKPAs(orgcode: String): Future[Map[String, KpiIndicator]]

}

object KpaService {
  def apply: KpaService = new KpaServiceImpl()
}

