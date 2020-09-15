package services.address

import domain.address.AddressType
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/07.
  */
class AddressTypeServiceTest extends FunSuite{

  test("testSaveAdressType"){
    val event = AddressType("124","Work Address")
    val service = Await.result(AddressTypeService.apply.save(event),2.minutes)
    assert(service.isExhausted)
  }

  test("testGetAddressTypeById"){
     val id="124"
     val idcode =Await.result(AddressTypeService.apply.findById(id),2 minutes)
    assert(idcode != null)
  }
  test("testgetAllAddressType"){
    val alltype = Await.result(AddressTypeService.apply.findAll,2 minutes)
    alltype.foreach(i =>println("**********All Address Type*********** ======>",i))
  }
}
