package controllers.financials

import conf.security.TokenCheck
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.{CustomerRejectedUploadsService, CustomerUploadsService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/09/27.
  */
class CustomerRejectedUploadsController extends Controller{

  def getCustomerUploadsByFileId(orgCode: String, fileId: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- CustomerRejectedUploadsService.apply.getCustomerUploadsByFileId(orgCode, fileId) // if auth.status == "VALID"
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getUploadByOrganisation(orgCode: String)= Action.async {
    request =>
      CustomerRejectedUploadsService.apply.getUploadByOrganisation(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUploadsYear(orgCode: String, year: Int)= Action.async {
    request =>
      CustomerRejectedUploadsService.apply.getUploadsYear(orgCode,year) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUploadsByMonth(orgCode: String, year: Int, month: Int)= Action.async {
    request =>
      CustomerRejectedUploadsService.apply.getUploadsByMonth(orgCode,year,month) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String)= Action.async {
    request =>
      CustomerRejectedUploadsService.apply.getUploadeByCode(orgCode,year,month,accountingCode) map (result =>
        Ok(Json.toJson(result)))
  }
}
