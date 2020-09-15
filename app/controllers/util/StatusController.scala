package controllers.util

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.StatusService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class StatusController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[domain.util.Status](input).get
      val results = StatusService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(id: String) = Action.async {
    request =>
      StatusService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      StatusService.getAll map (result =>
        Ok(Json.toJson(result)))
  }

}
