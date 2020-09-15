package controllers.financials

import domain.financials.admin.AccountSystems
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.AccountSystemService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/10/15.
  */
class AccountSystemsController extends Controller{
  def saveOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AccountSystems](input).get
      val results = AccountSystemService.apply.saveOrupdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getAccountingSystemById(accountingSystemId: String)= Action.async{
    request =>
      AccountSystemService.apply.getAccountingSystemById(accountingSystemId) map (result =>
        Ok(Json.toJson(result)))
  }
  def getAllAccountingSystems= Action.async{
    request =>
      AccountSystemService.apply.getAllAccountingSystems map (result =>
        Ok(Json.toJson(result)))
  }
}
