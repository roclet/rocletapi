package controllers.util

import domain.util.Race
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.util.RaceService
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Created by hashcode on 2015/11/09.
 */
class RaceController extends Controller{

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Race](input).get
      val results = RaceService.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getById(id: String) = Action.async {
    request =>
      RaceService.get(id) map (result =>
        Ok(Json.toJson(result)))
  }

  def getAll = Action.async {
    request =>
      RaceService.getAll map (result =>
        Ok(Json.toJson(result)))
  }
}
