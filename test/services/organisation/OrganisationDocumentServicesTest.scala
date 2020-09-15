package services.organisation

import domain.organisation.OrganisationDocuments
import domain.people.User
import org.joda.time.DateTime
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class OrganisationDocumentServicesTest extends FunSuite{

  test("Testsave") {
    val orgDoc = OrganisationDocuments("MMM-7190","kuminga.png","Application Form",new DateTime(),"png")
    val service = Await.result(OrganisationDocumentServices.apply.save(orgDoc),2 minutes)
    assert(service.isExhausted)
  }
  test("getOrganisationsDocByCode") {
    val orgCode="MMM-7190"
    val OrgCode =Await.result(OrganisationDocumentServices.apply.getOrganisationsDocByCode(orgCode),2 minutes)
    OrgCode.foreach(i =>println("**********getOrganisationsDocByCode*********** ======>",i))
  }
  test("getfindById") {
    val orgCode="MMM-7190"
    val url="kuminga.png"
    val OrgCode =Await.result(OrganisationDocumentServices.apply.findById(orgCode,url),2 minutes)
    OrgCode.foreach(i =>println("**********getfindById*********** ======>",i))
  }
}
