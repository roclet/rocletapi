package repositories.financials

import conf.connection.DataConnection
import domain.financials.FinanceStatementCategoryCodeMapping
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
import java.util.Date

import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/09/01.
  * */
class FinanceStatementCategoryTest extends FeatureSpec with GivenWhenThen{

  feature("Finance Statement Categories") {
    info("Finance Statement Categories")
    scenario("Finance Statement Categories") {

      Given("id,ctype,categories,subcategories,startCode,endCode,sessionId,date")
      val datep=new Date()
      val FinanceStatementCat=FinanceStatementCategoryCodeMapping("MMTT561","10027","INCOME STATEMENT","SALES","SALES",1000,1999,"120001",datep)
      And(" Given the Repository to Persist Try balance")
      val FinanceStatementRepo = FinanceStatementCategoryCodeMappingRepository
      print(" Object Obtained ", FinanceStatementRepo)
      FinanceStatementRepo.save(FinanceStatementCat)
      println(" ============================================ ")
//      val retrieveddata = Await.result(FinanceStatementRepo.getIncomeStatementCategoryById("MMTT561","10027"),2 minutes)
//      retrieveddata.foreach(i => println("By FinanceStatement=======>",i))
    }
  }
}
