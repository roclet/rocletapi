package controllers.hr

import conf.security.TokenCheck
import domain.hr.Employee
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.hr.EmployeeService
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by hashcode on 2017/04/07.
  */
class EmployeeController extends Controller {

  def createOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Employee](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- EmployeeService.apply.save(entity) if (auth.status == "VALID")
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

  def getEmployeeDetails(userId: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- EmployeeService.apply.getEmployeeDetails(userId) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

  def getAllEmployees(orgcode:String)= Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- EmployeeService.apply.getOrganisationEmployees(orgcode) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
