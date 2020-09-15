package repositories.financials

import domain.financials.ReferenceUploads
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.joda.time.DateTime

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/06.
  */
class ReferenceUploadsTest extends FeatureSpec with GivenWhenThen{

  feature("Reference Uploads") {
    info("Reference Uploads")
    scenario("Reference Uploads") {

                                  Given("orgCode,sessionId,referenceId,date,login,username,fullname,url,uploadSettingsId")
//      val datep=new DateTime
//      val Reference=ReferenceUploads("4001","111002","777701",datep,"Login","kuminga","lodge","ghhhhh.png","99999")
//      And(" Given the Repository to Persist Try balance")
//      val referenceuploads = ReferenceUploadsRepository
//      print(" Object Obtained ", referenceuploads)
//      referenceuploads.save(Reference)

    }
  }
}
