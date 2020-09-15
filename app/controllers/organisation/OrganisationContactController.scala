package controllers.organisation

import conf.security.TokenCheck
import domain.organisation.OrganisationContact
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisation._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/09/18.
  */
class   OrganisationContactController extends Controller{


  def createOrupdate =Action.async(parse.json){
      request=>
        val input = request.body
        val entity = Json.fromJson[OrganisationContact](input).get
        val response = for {
          auth <- TokenCheck.getToken(request)
          results <- OrganisationContactService.apply.createOrupdate(entity) if (auth.status == "VALID")
        } yield results
        response.map(ans => Ok(Json.toJson(entity)))
          .recover {
            case e: Exception => Unauthorized
          }
  }
  def getByorgCode(orgCode:String)=Action.async{
    request=>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationContactService.apply.findOrganisationContactByorgCode(orgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
  def getByemail(orgCode:String,email:String)=Action.async{
    request=>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationContactService.apply.findOrganisationContactByemail(orgCode,email) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
  def getById(orgCode:String,email:String,id:String)=Action.async{
    request=>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationContactService.apply.findOrganisationContactById(orgCode,email,id) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
