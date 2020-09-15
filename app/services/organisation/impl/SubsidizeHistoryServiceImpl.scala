package services.organisation.impl

import com.websudos.phantom.dsl.ResultSet
import domain.organisation.SubsidizeHistory
import repositories.organisation.SubsidizeHistoryRepository
import services.Service
import services.organisation.SubsidizeHistoryService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/22.
  */
class SubsidizeHistoryServiceImpl extends SubsidizeHistoryService with Service {
  override def save(fundinghistory: SubsidizeHistory): Future[ResultSet] = {
    SubsidizeHistoryRepository.save(fundinghistory)
  }

  override def getbyfundeCode(funderCode: String): Future[Seq[SubsidizeHistory]] = {
    SubsidizeHistoryRepository.getbyfundeCode(funderCode)
  }

  override def getorgCode(funderCode: String, orgCode: String): Future[Option[SubsidizeHistory]] = {
    SubsidizeHistoryRepository.getorgCode(funderCode, orgCode)
  }
}
