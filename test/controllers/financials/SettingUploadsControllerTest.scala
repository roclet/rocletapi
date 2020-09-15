package controllers.financials

import java.util.Date

import conf.util.Util
import domain.financials.SettingUploads
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._
/**
  * Created by kuminga on 2016/10/18.
  */
class SettingUploadsControllerTest extends PlaySpec with OneAppPerTest{

  "SettingUploadsController" should {

    "return Results" in {
      val mappinguplod=SettingUploads("1002","302","pastel","yyyy-mm-dd",2,2,3,4,2,2,4,"active","3333")
      val request = route(app, FakeRequest(POST, "/finance/uploadsetting").withJsonBody(Json.toJson(mappinguplod))).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is ", contentAsString(request))
    }
  }
}
