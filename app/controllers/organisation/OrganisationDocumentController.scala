package controllers.organisation

import conf.security.TokenCheck
import domain.organisation.OrganisationDocuments
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.organisation._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
/**
  * Created by kuminga on 2016/09/18.
  */
class OrganisationDocumentController extends Controller{
   def createOrupdate=Action.async(parse.json){
     request=>
       val input = request.body
       val entity = Json.fromJson[OrganisationDocuments](input).get
       val response = for {
         auth <- TokenCheck.getToken(request)
         results <- OrganisationDocumentServices.apply.save(entity) if (auth.status == "VALID")
       } yield results
       response.map(ans => Ok(Json.toJson(entity)))
         .recover {
           case e: Exception => Unauthorized
         }
   }
   def getDocByCode(orgCode:String)=Action.async{
     request=>
       val response = for {
         auth <- TokenCheck.getTokenfromParam(request)
         results <- OrganisationDocumentServices.apply.getOrganisationsDocByCode(orgCode) if auth.status == "VALID"
       } yield results
       response.map(ans => Ok(Json.toJson(ans)))
         .recover {
           case e: Exception => Unauthorized
         }
   }
   def getDocById(orgCode:String,url: String)=Action.async{
     request=>
       val response = for {
         auth <- TokenCheck.getTokenfromParam(request)
         results <- OrganisationDocumentServices.apply.findById(orgCode,url) if auth.status == "VALID"
       } yield results
       response.map(ans => Ok(Json.toJson(ans)))
         .recover {
           case e: Exception => Unauthorized
         }
   }
}
