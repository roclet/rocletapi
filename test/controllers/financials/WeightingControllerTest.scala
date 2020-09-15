package controllers.financials

import domain.reports.Weighting
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by hashcode on 2017/04/16.
  */
class WeightingControllerTest extends PlaySpec with OneAppPerTest{

  "SettingUploadsController" should {

    "return Results" in {
      val weighting=Weighting("orgcode","Probability",40.0)
      val request = route(app, FakeRequest(POST, "/reports/weightings/create").withJsonBody(Json.toJson(weighting))).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is ", contentAsString(request))
    }
  }
}
