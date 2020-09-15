package controllers.organisation

import conf.util.Util
import domain.organisation.Organisation
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by hashcode on 2016/10/05.
  */
class OrganisationControllerTest extends PlaySpec with OneAppPerTest {

  "MailController" should {

    "return Results" in {
      val organisation = Organisation(
        "670",
        "Telkom Pty Limited makashi",
        Util.md5Hash("232"),
        "ACTIVE", "makashi@kabaso.com", Map()
      )
      val request = route(app, FakeRequest(POST, "/organisation/createorganisation").withJsonBody(Json.toJson(organisation))).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is ", contentAsString(request))

      //        contentAsString(index) must include("Add Person")
    }
  }

}
