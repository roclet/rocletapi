package controllers.organisation

import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by kuminga on 2016/10/15.
  */
class GetOrganisationUsersControllerTest extends PlaySpec with OneAppPerTest{


  "GetOrganisationUserController" should {

    "display organisation type" in {
       val oga="MARGINM";
      val request = route(app, FakeRequest(GET, "/users/organisation/"+oga)
        .withHeaders( AUTHORIZATION -> "eyJhbGciOiJIUzI1NiIsImN0eSI6Ik1hcmdpbiBNZW50b3IiLCJ0eXAiOiJKV1QifQ.eyJqaXQiOiJjZDJkMzk5MTYwNzc1OGRmZTAzYmMzNjQwYmQ2ZWMzYiIsInJvbGUiOiJMaXN0KEFETUlOKSIsImV4cCI6ODkxMTAwMDAsImNyZWF0aW9uIjoiMjAxNi0xMC0wNlQxMzo1NjozMC45MTYrMDI6MDAiLCJpYXQiOjEwMDEsInN1YiI6ImFkbWluQHRlc3QuY29tIiwiaXNzIjoiTUFSR0lOTSJ9.MJmNzS_OF1CI77tNEs6yR2QI_VdVQVPCUfiEreg6JUc")
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content this is organisation users", contentAsString(request))

      //        contentAsString(index) must include("Add Person")
    }
  }


}
