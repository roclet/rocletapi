package controllers.financials

import java.util.Date

import conf.util.Util
import domain.financials.admin.CodeMappingSystem
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

/**
  * Created by kuminga on 2016/10/17.
  */
class CodeMappingSystemControllerTest extends PlaySpec with OneAppPerTest {

  "CodeMappingSystemControllerr" should {

    "return Results" in {
      //      val codemappingsystem = CodeMappingSystem(
      //        "pastel",
      //        "INCOME STATEMENT",
      //        "SALE",
      //        "SALE",
      //        100,
      //        400,
      //        "eyJhbGciOiJIUzI1NiIsImN0eSI6Ik1hcmdpbiBNZW50b3IiLCJ0eXAiOiJKV1QifQ.eyJqaXQiOiI1ZDM5NjhjZmEwNzIzNDlkMzgxMWVkNTkzNzZiOTEzZCIsInJvbGUiOiJMaXN0KEFETUlOKSIsImV4cCI6MTQ3NjIxMDI5MTMzNiwiY3JlYXRpb24iOjE0NzYxNjcwOTEzMzYsImlhdCI6MTQ3NjE2NzA5MTMzNiwic3ViIjoiYWRtaW5AdGVzdC5jb20iLCJpc3MiOiJNQVJHSU5NIn0.9WgXKoHXS8nDT_F6PS4Qhkqp7EhJ6pWCe8zd6NObAkU",
      //        new Date()
      //      )

      val oga = "MARGINM";
      val request = route(app, FakeRequest(GET, "/statement/customerupload/" + oga)
        .withHeaders(AUTHORIZATION -> "eyJhbGciOiJIUzI1NiIsImN0eSI6Ik1hcmdpbiBNZW50b3IiLCJ0eXAiOiJKV1QifQ.eyJqaXQiOiI5Y2M5M2IxODllZGE2ODFkMzA3NTQ3ZjI1NDc4YTc1YyIsInJvbGUiOiJBRE1JTiIsImV4cCI6MTQ3ODMzMzA4NzkxOCwiY3JlYXRpb24iOjE0NzgyODk4ODc5MTgsImlhdCI6MTQ3ODI4OTg4NzkxOCwic3ViIjoiYWRtaW5AdGVzdC5jb20iLCJpc3MiOiJNQVJHSU5NIn0.mgVrfqG8PTTCAZ6TjMXR2sG8RuzrwUoxlaCxIKLc2Sw")
      ).get
      status(request) mustBe OK
      contentType(request) mustBe Some("application/json")
      println(" The Content this is organisation users", contentAsString(request))
    }
  }

}
