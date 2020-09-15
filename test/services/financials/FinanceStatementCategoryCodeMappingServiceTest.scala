package services.financials

import domain.financials.FinanceStatementCategoryCodeMapping
import java.util.Date
import org.scalatest.{BeforeAndAfterEach, FunSuite}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by kuminga on 2016/09/24.
  */
class FinanceStatementCategoryCodeMappingServiceTest extends FunSuite{
  val orgaCode = "MARGINM"

  val datep = new Date()

//  test("cretaeOrUpdate"){
//    val refUploads=FinanceStatementCategoryCodeMapping("MMTT561","100234","COST OF SALES","COST OF SALES","SALES",2000,3999,"120001",datep)
//
//    val Ref = Await.result(FinanceStatementCategoryCodeMappingService.apply.createOrupdate(refUploads), 2.minutes)
//    assert(Ref.isExhausted)
//  }

  test("getOrgCategories") {

    val findByorgCod =Await.result(FinanceStatementCategoryCodeMappingService.apply.getOrgCategories(orgaCode),2 minutes)
    findByorgCod.foreach(i =>println("**********getOrgCategories*********** ======>",i))
  }
//  test("getCategoriesByFinType") {
//
//    val findByorgCod =Await.result(FinanceStatementCategoryCodeMappingService.apply.getCategoriesByFinType(orgaCode,"10027"),2 minutes)
//    findByorgCod.foreach(i =>println("**********getOrgCategories*********** ======>",i))
//  }

}
