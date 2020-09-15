package controllers.financials

import domain.financials.CustomerUploads
import domain.financials.FinanceStatementCategoryCodeMapping
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.CustomerUploadsService
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/27.
  */
class CustomerUploadsController extends Controller{

  def getUploadByOrganisation(orgCode: String)= Action.async {
    request =>
      CustomerUploadsService.apply.getUploadByOrganisation(orgCode) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUploadsYear(orgCode: String, year: Int)= Action.async {
    request =>
      CustomerUploadsService.apply.getUploadsYear(orgCode,year) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUploadsByMonth(orgCode: String, year: Int, month: Int)= Action.async {
    request =>
      CustomerUploadsService.apply.getUploadsByMonth(orgCode,year,month) map (result =>
        Ok(Json.toJson(result)))
  }
  def getUploadeByCode(orgCode: String, year: Int, month: Int, accountingCode: String)= Action.async {
    request =>
      CustomerUploadsService.apply.getUploadeByCode(orgCode,year,month,accountingCode) map (result =>
        Ok(Json.toJson(result)))
  }

  def getCustomerUploadsByFileId(orgCode: String, fileId: String) = Action.async {
    request =>
      CustomerUploadsService.apply.getCustomerUploadsByFileId(orgCode,fileId) map (result =>
        Ok(Json.toJson(result)))
  }
  def doesUploadExist(orgCode:String, year:Int, month:Int)= Action.async {
    request =>
      CustomerUploadsService.apply.doesUploadExist(orgCode,year,month) map (result =>
        Ok(Json.toJson(result)))
  }



}
