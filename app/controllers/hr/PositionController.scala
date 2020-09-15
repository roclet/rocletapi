package controllers.hr

import com.websudos.phantom.dsl.ResultSet
import conf.security.TokenCheck
import domain.hr.Position
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.hr.PositionService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2017/04/07.
  */
class PositionController extends Controller {


  def createOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Position](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- PositionService.apply.save(entity) if (auth.status == "VALID")
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

  def getPositions = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- PositionService.apply.getPositions if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            e.printStackTrace()
            Unauthorized
          }
        }
  }

  def getPosition(id: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- PositionService.apply.getPosition(id) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
