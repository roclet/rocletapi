package controllers.financials

import conf.security.{LoggedInUser, TokenCheck}
import conf.util.MarginKeys
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.IncomeStatementService
import services.util.ApiKeysService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/02/02.
  */
class IncomeStatementController extends Controller{



  def getIncomeStatementByMonth(orgCode:String,year:Int) = Action.async {

    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        loggedInUser <- LoggedInUser.user(orgCode, email.get) //if auth.status == "VALID"
        goUrl <- ApiKeysService.apply.get(MarginKeys.GO_URL)
        processFile <- IncomeStatementService.apply.getIncomeStatementByMonth(orgCode, year,goUrl.get.value)
      } yield processFile+goUrl.get.value

      response.map(resp => Ok(Json.toJson("MEssage.."+resp)))
        .recover {
          case e: Exception => {
            println(e.getMessage())
            Unauthorized
          }
        }
  }

  def getIncomeStatementByYear(orgCode:String,start:Int, end:Int) = Action.async {
    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        goUrl <- ApiKeysService.apply.get(MarginKeys.GO_URL)
        processFile <- IncomeStatementService.apply.getIncomeStatementByYear(orgCode, start, end,goUrl.get.value)

      } yield processFile

      response.map(resp => Ok(Json.toJson("MEssage"+resp)))
        .recover {
          case e: Exception => {
            println(e.getMessage())
            Unauthorized
          }
        }
  }


}
