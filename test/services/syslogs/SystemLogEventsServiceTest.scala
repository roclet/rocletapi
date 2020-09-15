package services.syslogs

import domain.syslogs.SystemLogEvents
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._


/**
  * Created by hashcode on 2016/09/07.
  */
class SystemLogEventsServiceTest extends FunSuite {

  test("testGetOrganisationLogEvent") {
    val event = SystemLogEvents("MMM","1","EMAIL","EMAIL","Email Sent",new DateTime())
    val service = Await.result(SystemLogEventsService.apply.save(event),2.minutes)
    assert(service.isExhausted)


  }

  test("testGetOrganisationLogs") {
    val orgCode="MMM"
    val logs = Await.result(SystemLogEventsService.apply.getOrganisationLogs(orgCode),2.minutes)
    assert(logs != null)
  }

  test("testSave") {
    val event = SystemLogEvents("MMM","1","EMAIL","EMAIL","Email Sent",new DateTime())
    val service = Await.result(SystemLogEventsService.apply.save(event),2.minutes)
    assert(service.isExhausted)

  }

}
