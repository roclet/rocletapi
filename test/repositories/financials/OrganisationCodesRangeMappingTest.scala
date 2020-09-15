package repositories.financials

//import domain.financials.OrganisationCodesRangeMapping
import org.joda.time.DateTime
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/06.
  */
class OrganisationCodesRangeMappingTest extends FeatureSpec with GivenWhenThen{
  feature("Organisation Codes Range Mapping") {
    info("Organisation Codes Range Mapping")
    scenario("Organisation Codes Range Mapping") {

      Given("id,orgCode,categoryId,startValue,endValue,status,dateCreated,dateModified,sessionId")

//      val organisationCodesrangemapping=OrganisationCodesRangeMapping("111","10002","90000","89","200","active",new DateTime(),new DateTime(),"77777")
//      And(" Given the Repository to Persist Try balance")
//      val OrganisationCodesRangeMappingRepositoryRepo = OrganisationCodesRangeMappingRepository
//      print(" Object Obtained ", OrganisationCodesRangeMappingRepositoryRepo)
//      OrganisationCodesRangeMappingRepositoryRepo.save(organisationCodesrangemapping)
//      println(" ============================================ ")
//      val retrieveddata = Await.result(OrganisationCodesRangeMappingRepositoryRepo.getCodesRangeByorgCode("10002"),2 minutes)
//
//      val retrieveddatcatid = Await.result(OrganisationCodesRangeMappingRepositoryRepo.getCodesRangeBycategoryId("10002","90000"),2 minutes)
//      retrieveddatcatid.foreach(i => println("By FinanceStatement=======>",i))

    }
  }
}
