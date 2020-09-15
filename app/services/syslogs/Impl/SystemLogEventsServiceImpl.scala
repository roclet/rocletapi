package services.syslogs.Impl

import com.datastax.driver.core.ResultSet
import domain.syslogs.SystemLogEvents
import repositories.syslogs.SystemLogEventsRepository
import services.Service
import services.syslogs.SystemLogEventsService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/07.
  */
class SystemLogEventsServiceImpl extends SystemLogEventsService with Service{
  override def save(log: SystemLogEvents): Future[ResultSet] = {
    SystemLogEventsRepository.save(log)
  }

  override def getOrganisationLogEvent(orgCode: String, id: String): Future[Option[SystemLogEvents]] = {
    SystemLogEventsRepository.getOrganisationLogEvent(orgCode,id)
  }

  override def getOrganisationLogs(orgCode: String): Future[Seq[SystemLogEvents]] = {
    SystemLogEventsRepository.getOrganisationLogs(orgCode)
  }
}
