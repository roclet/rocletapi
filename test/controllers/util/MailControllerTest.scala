package controllers.util

import com.google.gson.Gson
import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._


/**
  * Created by hashcode on 2016/09/07.
  */

class MailControllerTest extends PlaySpec with OneAppPerTest {
  val gson = new Gson()

  "Routes" should {

    "send 404 on a bad request" in {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

    "MailController" should {

      "return Results" in {
        val request = route(app, FakeRequest(GET, "/util/mail/MARGINM") .withHeaders( AUTHORIZATION -> "eyJhbGciOiJIUzI1NiIsImN0eSI6Ik1hcmdpbiBNZW50b3IiLCJ0eXAiOiJKV1QifQ.eyJqaXQiOiJjZDJkMzk5MTYwNzc1OGRmZTAzYmMzNjQwYmQ2ZWMzYiIsInJvbGUiOiJMaXN0KEFETUlOKSIsImV4cCI6ODkxMTAwMDAsImNyZWF0aW9uIjoiMjAxNi0xMC0wNlQxMzo1NjozMC45MTYrMDI6MDAiLCJpYXQiOjEwMDEsInN1YiI6ImFkbWluQHRlc3QuY29tIiwiaXNzIjoiTUFSR0lOTSJ9.MJmNzS_OF1CI77tNEs6yR2QI_VdVQVPCUfiEreg6JUc")
        ).get
        status(request) mustBe OK
        contentType(request) mustBe Some("application/json")
        println(" The Output", contentAsJson(request))
      }


    }
  }
}