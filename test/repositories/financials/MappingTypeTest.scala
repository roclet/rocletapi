package repositories.financials

import conf.connection.DataConnection
import domain.financials.admin.MappingType
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/*import java.util.Date*/

import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/09/05.
  */
class MappingTypeTest extends FeatureSpec with GivenWhenThen{


  feature("Mapping type") {
    info("Mapping type")
    scenario("Mapping type") {

      Given("id,name,description")

      val mappingtype=MappingType("3001","range mapping","range mapping")
      And(" Given the Repository to Persist Try balance")
      val MappingTypRepo = MappingTypeRepository
      print(" Object Obtained ", MappingTypRepo)
      MappingTypRepo.save(mappingtype)
      println(" ============================================ ")
      val retrieveddata = Await.result(MappingTypRepo.getMappingTypeById("3001"),2 minutes)
      retrieveddata.foreach(i => println("By FinanceStatement=======>",i))
      val retrieveddataAll = Await.result(MappingTypRepo.findAll,2 minutes)
      retrieveddataAll.foreach(i => println("By FinanceStatement=======>",i))
    }
  }
}
