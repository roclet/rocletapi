package controllers.financials

import domain.financials.admin.{CodeMappingSystem, MappingType}
import domain.financials.FinanceStatementCategoryCodeMapping
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.financials.{CodeMappingSystemService, FinanceStatementCategoryCodeMappingService, MappingTypeService}

import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/10/15.
  */
class CodeMappingSystemController extends Controller{

  def saveOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[CodeMappingSystem](input).get
      val results = CodeMappingSystemService.apply.saveOrupdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getCodeMappingSystemById(accountSystemId:String,id:String)= Action.async{
    request =>
      CodeMappingSystemService.apply.getCodeMappingSystemById(accountSystemId,id) map (result =>
        Ok(Json.toJson(result)))
  }
  def getAllCodemappingSystems(accountSystemId:String)= Action.async{
    request =>
      CodeMappingSystemService.apply.getAllCodemappingSystems(accountSystemId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAllCodeMappings= Action.async{
    request =>
      CodeMappingSystemService.apply.getAllCodeMapping map (result =>
        Ok(Json.toJson(result)))
  }


  def saveOrupdateFinanceStatementCategoryCodeMapping = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FinanceStatementCategoryCodeMapping](input).get
      val results = FinanceStatementCategoryCodeMappingService.apply.createOrupdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getCategoriesByFinType(accountSystemId:String,id:String)= Action.async{
    request =>
      FinanceStatementCategoryCodeMappingService.apply.getCategoriesByFinType(accountSystemId,id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getOrgCategories(accountSystemId:String)= Action.async{

    request =>

      FinanceStatementCategoryCodeMappingService.apply.getOrgCategories(accountSystemId) map (result =>
        Ok(Json.toJson(result)))
  }

  def getSubCategories(accountSystemId:String,id:String, category:String)= Action.async{
    request =>
      FinanceStatementCategoryCodeMappingService.apply.getSubCategories(accountSystemId,id,category) map (result =>
        Ok(Json.toJson(result)))
  }

  def saveOrupdateMappingType = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[MappingType](input).get
      val results = MappingTypeService.apply.createOrupdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getMappingTypeById(id:String)= Action.async{
    request =>
      MappingTypeService.apply.getMappingTypeById(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def findAll= Action.async{
    request =>
      MappingTypeService.apply.findAll map (result =>
        Ok(Json.toJson(result)))
  }




}
