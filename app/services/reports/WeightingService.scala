package services.reports

import com.websudos.phantom.dsl.ResultSet
import domain.reports.Weighting
import services.reports.Impl.WeightingServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/12.
  */
trait WeightingService {

  def save(weighting: Weighting): Future[ResultSet]

  def getWeighting(orgCode: String, kpa: String): Future[Option[Weighting]]

  def getWeitings(orgcode: String): Future[Seq[Weighting]]

}

object WeightingService{
  def apply: WeightingService = new WeightingServiceImpl()
}
