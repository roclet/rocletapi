package services.reports.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.reports.Weighting
import repositories.reports.WeightingRepository
import services.Service
import services.reports.WeightingService

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/12.
  */
class WeightingServiceImpl extends WeightingService with Service {

  override def save(weighting: Weighting): Future[ResultSet] = {
    WeightingRepository.save(weighting)
  }

  override def getWeighting(orgCode: String, kpa: String): Future[Option[Weighting]] = {
    WeightingRepository.getWeighting(orgCode, kpa)
  }

  override def getWeitings(orgcode: String): Future[Seq[Weighting]] = {
    WeightingRepository.getWeitings(orgcode)
  }
}


