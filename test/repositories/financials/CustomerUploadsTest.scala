package repositories.financials

import conf.connection.DataConnection
import domain.financials.CustomerUploads
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.concurrent.Await
import scala.concurrent.duration._
/*import java.util.Date*/

import org.joda.time.DateTime
/**
  * Created by kuminga on 2016/08/29.
  */
class CustomerUploadsTest extends FeatureSpec with GivenWhenThen{

  feature("Customser Upload") {
    info("As a Customser you Upload Try balance")
    scenario("Upload Try balance ") {

      Given("Given a csv file with orgCode,id,reference,date,accountingCode," +
        "year,month,day,accountingSystem,entryType,entryDescription,entryCategory," +
        "entryValue,csvStringInput,mappingCode,isFeature")

      val datep=new DateTime()
//      val customeruploads = CustomerUploads("SD7012",',"0042",datep,"899908762",2017,5,10,"Pastel test",5000,8000,"Salary","debit","youtube","yuo8992","human right","p905050")

//      And(" Given the Repository to Persist Try balance")
//      val CustomerUploadsRepo = CustomerUploadsRepository
//      val customeruploadsbycategoryRepo = CustomerUploadsByCategoryRepository
//      print(" Object Obtained ", customeruploads)
//      When("When I persist User Sessions")
//      CustomerUploadsRepo.save(customeruploads)
//      println(" The Save Executed ")
//
//      Then("Testing  by org Code")
//      val retrievedByorgCode=Await.result(CustomerUploadsRepo.getUploadByOrganisation("SD7013"), 2 minutes)
//      retrievedByorgCode.foreach(i => println("By OrgCode=======>",i))
//
//      Then("Testing  by organisation Code")
//      val retrievedByCategory = Await.result(CustomerUploadsRepo.getUploadeByCode("SD7013",2016,4,"899908762"), 2 minutes)
//      retrievedByCategory.foreach(i => println("By Category=======>",i.entryCategory))
//      Then("Testing by Month")
//      val retrievedByMonth = Await.result(CustomerUploadsRepo.getUploadsByMonth("SD7013",2016,4), 2 minutes)
//      retrievedByMonth.foreach(i => println("By Month=======>",i.month))
//      Then("Testing by Month")
//      val retrievedByYear = Await.result(CustomerUploadsRepo.getUploadsYear("SD7013",2016), 2 minutes)
//      retrievedByYear.foreach(i => println("By Year=======>",i.year))
//
//      val retrievedBycata = Await.result(customeruploadsbycategoryRepo.getCustomerUploadsByCategory("SD7013","Salary"), 2 minutes)
//      retrievedBycata.foreach(i => println("By Year=======>",i))
////      assert(retrievedByorgCode.size>0)
    }
  }
}
