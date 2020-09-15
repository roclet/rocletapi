package services.address


import domain.address.LocationType
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class LocationTypeServiceTest extends FunSuite{

    test("testsaveLocationTypeService"){
      val event = LocationType("1246","Work Address","you880","Africa South")
      val locType = Await.result(LocationTypeService.apply.save(event),2.minutes)
      assert(locType.isExhausted)
    }
    test("testGfindById"){
    val id="1246"
    val idcode =Await.result(LocationTypeService.apply.findById(id),2 minutes)
    assert(idcode != null)
   }
  test("testGetAddressTypeById"){
    val id="1246"
    val idcode =Await.result(LocationTypeService.apply.findAll,2 minutes)
    idcode.foreach(i =>println("**********All Address Type*********** ======>",i))
  }
}
