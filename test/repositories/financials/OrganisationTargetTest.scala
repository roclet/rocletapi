package repositories.financials


import java.util.Date

import domain.financials.OrganisationTarget
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/06.
  */
class OrganisationTargetTest extends FeatureSpec with GivenWhenThen{
  feature("Organisation Target") {
    info("Organisation Target")
    scenario("Organisation Target") {

      Given("orgCode,id,description,targetValue,date")

      val organisationtarget=OrganisationTarget("90001","1929","galannnnnd",90000,new Date)
      And(" Given the Repository to Persist Try balance")
      val OrganisationTargetRepositoryRepo = OrganisationTargetRepository
      print(" Object Obtained ", OrganisationTargetRepositoryRepo)
      OrganisationTargetRepositoryRepo.save(organisationtarget)
      println(" ============================================ ")
      val retrieveddata = Await.result(OrganisationTargetRepositoryRepo.findByorgCode("90011"),2 minutes)

      val retrieveddatId = Await.result(OrganisationTargetRepositoryRepo.findById("90011","1929"),2 minutes)
      retrieveddatId.foreach(i => println("By FinanceStatement=======>",i))

    }
  }
}
