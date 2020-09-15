package services.financials


import domain.financials.SettingUploads
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by kuminga on 2016/09/24.
  */
class SettingUploadsServiceTest extends FunSuite{

  val orgaCode = "1002"
  val id="2019"

//  test("createUser"){
//    val mappinguplod=SettingUploads("1002","302","pastel","yyyy-mm-dd",1,2,3,4,1,2,4,"active","3333")
//
//    val mappingupl = Await.result(SettingUploadsService.apply.save(mappinguplod), 2.minutes)
//    assert(mappingupl.isExhausted)
//  }
//
//  test("findByorgCode") {
//
//    val SettingUpl =Await.result(SettingUploadsService.apply.findByorgCode(orgaCode),2 minutes)
//    SettingUpl.foreach(i =>println("**********findByorgCode*********** ======>",i))
//  }
//  test("findById") {
//
//    val findById =Await.result(SettingUploadsService.apply.findById(orgaCode,id),2 minutes)
//    findById.foreach(i =>println("**********findById*********** ======>",i))
//  }

}
