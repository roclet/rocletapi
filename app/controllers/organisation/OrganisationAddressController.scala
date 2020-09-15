package controllers.organisation

import conf.security.TokenCheck
import domain.organisation.OrganisationAddress
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisation._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/17.
  */
class OrganisationAddressController extends Controller{

  def createOrupdate = Action.async(parse.json){
    request=>
      val input = request.body
      val entity = Json.fromJson[OrganisationAddress](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationAddressServices.apply.createOrupdate(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover { case e: Exception => Unauthorized }
  }
  def getAll(orgCode:String)=Action.async{

    request =>
      println("GatewayTimeout",orgCode)
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationAddressServices.apply.findOrgAddressByorgCode(orgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
  def getByEmail(orgCode:String,email:String)=Action.async{
    request =>

      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <-  OrganisationAddressServices.apply.findOrgAddressByemail(orgCode,email) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
  def getById(orgCode:String,email:String,id:String)=Action.async{
    request =>

      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationAddressServices.apply.findOrgAddressById(orgCode,email,id) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
