package controllers.address

import java.util.NoSuchElementException

import conf.security.TokenCheck
import domain.address.{CordinatesRequest, GlobalLocation}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.address.GlobalLocationServices

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/03.
  */
class GlobalLocationController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[GlobalLocation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- GlobalLocationServices.apply.saveOrUpdate(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getById(id: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- GlobalLocationServices.apply.getLocationById(id) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.get)))
        .recover {
          case e: NoSuchElementException => NotFound
          case e: Exception => Unauthorized
        }
  }

  def getAll = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- GlobalLocationServices.apply.getAllLocations if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def  getCordinates = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[CordinatesRequest](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- GlobalLocationServices.apply.getCordinates(entity.request) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
