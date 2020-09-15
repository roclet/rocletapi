package repositories.financials

import domain.financials.SettingUploads
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/05.
  */
class SettingUploadsTest extends FeatureSpec with GivenWhenThen {

    feature("Mapping type") {
      info("Mapping type")
      scenario("Mapping type") {

        Given("orgCode,id,mappingTypeId,accountingSystem,dateFormat,codeColumn,descriptionColumn,debitColumn,creditColumn,startRow,dateRow,dateColumn,status,sessionId")

        //        val mappinguplod=SettingUploads("1002","302","pastel","yyyy-mm-dd",1,2,3,4,1,2,4,"active","3333")
        //       And(" Given the Repository to Persist Try balance")
        //      val SettingUploadsRepo = SettingUploadsRepository
        //      print(" Object Obtained ", SettingUploadsRepo)
        //      SettingUploadsRepo.save(mappinguplod)
        //      println(" ============================================ ")
        //       val retrieveddata = Await.result(SettingUploadsRepo.findByorgCode("1002"),2 minutes)
        //
        //      val retrieveddatid = Await.result(SettingUploadsRepo.findById("1002","2019"),2 minutes)
        //        retrieveddatid.foreach(i => println("By FinanceStatement=======>",i))
        //      }
      }
    }
}