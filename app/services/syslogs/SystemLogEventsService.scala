package services.syslogs

import com.datastax.driver.core.ResultSet
import domain.syslogs.SystemLogEvents
import services.syslogs.Impl.SystemLogEventsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/07.
  */
trait SystemLogEventsService {
  def save(log: SystemLogEvents): Future[ResultSet]
  def getOrganisationLogEvent(orgCode: String, id: String): Future[Option[SystemLogEvents]]
  def getOrganisationLogs(orgCode: String): Future[Seq[SystemLogEvents]]
}
object SystemLogEventsService {
  def apply: SystemLogEventsService = new SystemLogEventsServiceImpl()
}
