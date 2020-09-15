package services.organisation

import domain.organisation.Subsidize
import domain.people.User
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class SubsidizeServiceTest extends FunSuite{

  test("TestsaveFunders") {
    val funds = Subsidize("FIN-5190","Cape University")
    val servicep = Await.result(SubsidizeService.apply.save(funds),2 minutes)
    assert(servicep.isExhausted)
  }
  test("getfindCode") {
    val orgCode="FIN-5190"
    val OrgCode =Await.result(SubsidizeService.apply.getfindCode(orgCode),2 minutes)
    OrgCode.foreach(i =>println("**********getfindCode*********** ======>",i))
  }
}
