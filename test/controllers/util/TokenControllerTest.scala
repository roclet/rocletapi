package controllers.util

import java.util.Date

import com.google.gson.Gson
import domain.util.{Credential, Mail}
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by hashcode on 2016/10/04.
  */
class TokenControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()

  "MailController" should {

    "return Results" in {
      val credential = Credential("admin@test.com","admin")
      val json = gson.toJson(credential).stripMargin
      println(" The Value is ", json)
      val request =  route(app, FakeRequest(POST, "/util/createtoken").withJsonBody(Json.parse(json))).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content is ", contentAsString(request))

      //        contentAsString(index) must include("Add Person")
    }
  }

}
