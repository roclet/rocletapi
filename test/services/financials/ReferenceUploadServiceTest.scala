package services.financials

import domain.financials.ReferenceUploads
import org.joda.time.DateTime
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/24.
  */
class ReferenceUploadServiceTest extends FunSuite{

  val orgaCode = "PTU1002"
  val sessionid="1904"
  val referenceId="7021"

  test("cretaeOrUpdate"){
//    val refUploads=ReferenceUploads("PTU1002","1904","7021",new DateTime(),"Login details","roclet","kuminga makashi","terrahom.png","421")

//    val Ref = Await.result(ReferenceUploadService.apply.cretaeOrUpdate(refUploads), 2.minutes)
//    assert(Ref.isExhausted)
  }

  test("findByorgCode") {

    val findByorgCod =Await.result(ReferenceUploadService.apply.findByorgCode(orgaCode),2 minutes)
    findByorgCod.foreach(i =>println("**********findByorgCode*********** ======>",i))
  }

  test("findById") {

    val referenceUpl =Await.result(ReferenceUploadService.apply.findById(orgaCode,sessionid,referenceId),2 minutes)
    referenceUpl.foreach(i =>println("**********ReferenceUploadService*********** ======>",i))
  }

  test("findBysessionId") {

    val referenceUplS =Await.result(ReferenceUploadService.apply.findBysessionId(orgaCode,sessionid),2 minutes)
    referenceUplS.foreach(i =>println("**********findBysessionId*********** ======>",i))
  }
}
