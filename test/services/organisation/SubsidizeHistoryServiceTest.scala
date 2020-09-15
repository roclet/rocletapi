package services.organisation

import domain.organisation.SubsidizeHistory
import domain.people.User
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class SubsidizeHistoryServiceTest extends FunSuite{

  test("TestsaveFundersH") {
    val funds = SubsidizeHistory("FIN-5190","MMM-5190",new DateTime(),"active")
    val serviceh = Await.result(SubsidizeHistoryService.apply.save(funds),2 minutes)
    assert(serviceh.isExhausted)
  }
  test("getgetbyfundeCode") {
    val orgCode="FIN-5190"
    val OrgCode =Await.result(SubsidizeHistoryService.apply.getbyfundeCode(orgCode),2 minutes)
    OrgCode.foreach(i =>println("**********getOrganisationsSubscriptions*********** ======>",i))
  }
  test("getorgCode") {
    val forgCode="FIN-5190"
    val orgcode="MMM-5190"
    val OrgCodep =Await.result(SubsidizeHistoryService.apply.getorgCode(forgCode,orgcode),2 minutes)
    OrgCodep.foreach(i =>println("**********getorgCode*********** ======>",i))
  }

}
