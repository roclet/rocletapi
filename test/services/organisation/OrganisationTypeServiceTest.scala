package services.organisation
import domain.organisation.OrganisationType
import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by kuminga on 2016/09/19.
  */
class OrganisationTypeServiceTest extends FunSuite{

//     test("testfuit"){
//          val event = OrganisationType("T001","Federation Politique")
//          val orgType = Await.result(OrganisationTypeService.apply.save(event),2 minutes)
//          assert(orgType.isExhausted)
//     }
//     test("getByOrganisationTypeId"){
//          val id="T001"
//          val idcode =Await.result(OrganisationTypeService.apply.getByOrganisationTypeId(id),2 minutes)
//          idcode.foreach(i =>println("**********org type by id*********** ======>",i))
//     }
     test("getAllOrg"){

          val idcode =Await.result(OrganisationTypeService.apply.getAllOrg,2 minutes)
          idcode.foreach(i =>println("**********all org type*********** ======>",i))
     }

}
