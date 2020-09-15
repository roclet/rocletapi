package services.organisation

import domain.organisation.OrganisationSubscriptions
import domain.people.User
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class OrganisationSubscriptionsServiceTest extends FunSuite{

  test("Testsave") {
    val orgSub = OrganisationSubscriptions("MMM-5190","k32","su23",new DateTime(),new DateTime(),"active")
    val service = Await.result(OrganisationSubscriptionsService.apply.save(orgSub),2 minutes)
    assert(service.isExhausted)
  }
  test("getOrganisationsSubscriptions") {
    val orgCode="MMM-5190"
    val OrgCode =Await.result(OrganisationSubscriptionsService.apply.getOrganisationsSubscriptions(orgCode),2 minutes)
    OrgCode.foreach(i =>println("**********getOrganisationsSubscriptions*********** ======>",i))
  }
  test("getBysubscriptionsId") {
    val orgCode="MMM-5190"
    val id="k32"
    val subs="su23"
    val OrgCode =Await.result(OrganisationSubscriptionsService.apply.getBysubscriptionsId(orgCode,id,subs),2 minutes)
    OrgCode.foreach(i =>println("**********getBysubscriptionsId*********** ======>",i))
  }
}
