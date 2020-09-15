package controllers.financials

import conf.security.{LoggedInUser, TokenCheck}
import domain.financials.{FinanceStatementCategoryCodeMapping, SettingUploads}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.{AccountSystemService, CodeMappingSystemService, FinanceStatementCategoryCodeMappingService, SettingUploadsService}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/10/18.
  */
class SettingUploadsController extends Controller {

  def saveOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[SettingUploads](input).get
      val results = SettingUploadsService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getOrganisationSettingUpload(org:String) = Action.async {
    request =>
      SettingUploadsService.apply.getOrganisationSettingUpload(org) map (result =>
        Ok(Json.toJson(result)))
  }

  def getOrganisationCodeMappings(org:String) = Action.async {
    request =>
      FinanceStatementCategoryCodeMappingService.apply.getOrgCategories(org) map (result =>
        Ok(Json.toJson(result)))
  }


  def getAccountingSystemById(accountingSystemId: String) = Action.async {
    request =>
      AccountSystemService.apply.getAccountingSystemById(accountingSystemId) map (result =>
        Ok(Json.toJson(result)))
  }


  def getUpdateWithAccountSystem(accountingSystemId: String) = Action.async {
    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode")
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        loggedInUser <- LoggedInUser.user(orgCode.get,email.get)  //if auth.status == "VALID"
        accountsystem <- AccountSystemService.apply.getAccountingSystemById(accountingSystemId)
        saveUploads <-SettingUploadsService.apply().save(SettingUploads(
          loggedInUser.get.orgCode,
          accountsystem.get.mappingTypeId,
          accountsystem.get.accountingSystemName,
          accountsystem.get.dateFormat,
          accountsystem.get.codeColumn,
          accountsystem.get.descriptionColumn,
          accountsystem.get.debitColumn,
          accountsystem.get.creditColumn,
          accountsystem.get.startRow,
          accountsystem.get.dateRow,
          accountsystem.get.dateColumn,
          accountsystem.get.status,accountsystem.get.sessionId))
      } yield saveUploads
      response.map(ans => Ok(Json.toJson( ans.isExhausted)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getUpdateWithCodeMapping(accountingSystemId: String) = Action.async {
    request =>
      val tokenParam = request.headers.get("Authorization")
      val email = request.headers.get("email")
      val orgCode = request.headers.get("orgCode")
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        loggedInUser <-  LoggedInUser.user(orgCode.get,email.get) // if auth.status == "VALID"
        codeMappings <- CodeMappingSystemService.apply.getAllCodemappingSystems(accountingSystemId)
        uploadResult <-FinanceStatementCategoryCodeMappingService.apply.loadCodeMappings(codeMappings,loggedInUser.get)
      } yield uploadResult

      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

}
