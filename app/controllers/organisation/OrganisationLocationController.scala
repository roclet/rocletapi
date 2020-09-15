package controllers.organisation

import com.datastax.driver.core.schemabuilder.TableOptions.CompactionOptions.DateTieredCompactionStrategyOptions
import com.websudos.phantom.dsl.DateTime
import conf.security.TokenCheck
import domain.organisation.OrganisationLocation
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisation._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/09/18.
  */
class OrganisationLocationController extends Controller {
  def createOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[OrganisationLocation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationLocationServices.apply.save(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getLocationById(orgCode: String, dateTime: Long) = Action.async {
    request =>
      val date = new DateTime(dateTime)
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationLocationServices.apply.getLocationById(orgCode, date) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getLocations(orgCode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationLocationServices.apply.getLocations(orgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCurrentLocation(orgCode: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationLocationServices.apply.getLocations(orgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.head)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

}
