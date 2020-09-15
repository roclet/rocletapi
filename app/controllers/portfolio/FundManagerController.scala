package controllers.portfolio

import conf.security.TokenCheck
import domain.portfolio.{FundManager, FundManagerStatus}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.portfolio.FundManagerService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/12.
  */
class FundManagerController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FundManager](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- FundManagerService.apply.save(entity) if auth.status == "VALID"
        results <-FundManagerService.apply.createStatus(FundManagerStatus(entity.fundOrgCode,entity.email,entity.smeOrgCode,new DateTime(),"ASSIGNED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def createStatus = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FundManagerStatus](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- FundManagerService.apply.createStatus(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getSme(fundOrgCode:String,email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundManagerService.apply.getSme(fundOrgCode,email,smeOrgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCurrentSmes(fundOrgCode:String, email:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundManagerService.apply.getCurrentSmes(fundOrgCode,email) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getOldSmes(fundOrgCode:String, email:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundManagerService.apply.getOldSmes(fundOrgCode,email) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getStatus(fundOrgCode:String,email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundManagerService.apply.getStatus(fundOrgCode,email,smeOrgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getAllSmes(funOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundManagerService.apply.getAllSmes(funOrgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCurrentStatus(funOrgCode:String,email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        sme <- FundManagerService.apply.getSme(funOrgCode,email,smeOrgCode)
        results <- FundManagerService.apply.getCurrentStatus(sme.get) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.status)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
