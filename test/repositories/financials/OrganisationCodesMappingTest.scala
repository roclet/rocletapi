package repositories.financials

import conf.connection.DataConnection
//import domain.financials.OrganisationCodesMapping
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/06.
  */
class OrganisationCodesMappingTest extends FeatureSpec with GivenWhenThen{



  feature("Organisation Codes Mapping") {
    info("Organisation Codes Mapping")
    scenario("Organisation Codes Mapping") {

      Given("orgCode,code,description,codeCategoryId,codeStatus,sessionId")

//      val organisationcodesmapping=OrganisationCodesMapping("90011","111","vetical fall","00001","active","88888")
//      And(" Given the Repository to Persist Try balance")
//      val OrganisationCodesMappingRepo = OrganisationCodesMappingRepository
//      print(" Object Obtained ", OrganisationCodesMappingRepo)
//      OrganisationCodesMappingRepo.save(organisationcodesmapping)
//      println(" ============================================ ")
//      val retrieveddata = Await.result(OrganisationCodesMappingRepo.findByorgCode("90011"),2 minutes)
//
//      val retrieveddatcat = Await.result(OrganisationCodesMappingRepo.findBycodeCat("90011","00001"),2 minutes)
//      retrieveddatcat.foreach(i => println("By FinanceStatement=======>",i))
//      val retrieveddatstatus = Await.result(OrganisationCodesMappingRepo.findBycodeStatus("90011","00001","active"),2 minutes)
//      retrieveddatstatus.foreach(i => println("By FinanceStatement=======>",i))
    }
  }
}
