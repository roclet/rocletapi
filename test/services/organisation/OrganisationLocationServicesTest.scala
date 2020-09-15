package services.organisation

import java.util.Date

import domain.organisation.OrganisationLocation
import domain.people.User
import org.scalatest.FunSuite
import org.joda.time.DateTime

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/22.
  */
class OrganisationLocationServicesTest extends FunSuite{

  test("OrganisationContact") {
    val orgLoc = OrganisationLocation("MMM-9190",new DateTime(),"Province","L0002")
    val service = Await.result(OrganisationLocationServices.apply.save(orgLoc),2 minutes)
    assert(service.isExhausted)
  }
  test("getLocations") {
    val orgCode="MMM-9190"
    val OrgCode =Await.result(OrganisationLocationServices.apply.getLocations(orgCode),2 minutes)
    OrgCode.foreach(i =>println("**********getLocations*********** ======>",i))
  }
  test("getLocationById") {
    val orgCode="MMM-9190"
    val id="R123"
    val OrgCode =Await.result(OrganisationLocationServices.apply.getLocationById(orgCode,new DateTime()),2 minutes)
    OrgCode.foreach(i =>println("**********getLocationById*********** ======>",i))
  }
}
