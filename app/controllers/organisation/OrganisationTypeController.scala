package controllers.organisation

import conf.security.TokenCheck
import domain.organisation.OrganisationType
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisation._

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/09/18.
  */
class OrganisationTypeController extends Controller {

  def createOrupdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[OrganisationType](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationTypeService.apply.save(entity) if (auth.status == "VALID")
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }

  def getAllOrganisationType = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationTypeService.apply.getAllOrg if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            e.printStackTrace()
            Unauthorized
          }
        }
  }


  def getById(id: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationTypeService.apply.getByOrganisationTypeId(id) if (auth.status == "VALID")
      } yield results
      response.map(result => Ok(Json.toJson(result)))
        .recover { case e: Exception => Unauthorized }
  }

}
