package controllers.portfolio

import conf.security.TokenCheck
import domain.portfolio.{AdvisorPortfolio, AdvisorPortfolioStatus}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.portfolio.AdvisorPortfolioService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/12.
  */
class AdvisorPortfolioController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AdvisorPortfolio](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- AdvisorPortfolioService.apply.save(entity) if auth.status == "VALID"
        results <-AdvisorPortfolioService.apply.createStatus(AdvisorPortfolioStatus(entity.email,entity.smeOrgCode,new DateTime(),"ASSIGNED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def createStatus = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[AdvisorPortfolioStatus](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- AdvisorPortfolioService.apply.createStatus(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCurrentPortfolio(email: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- AdvisorPortfolioService.apply.getCurrentPortfolio(email) // if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getSme(email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- AdvisorPortfolioService.apply.getSme(email,smeOrgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }


  def getStatus(email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- AdvisorPortfolioService.apply.getStatus(email,smeOrgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getOldPortfolio(email:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- AdvisorPortfolioService.apply.getOldPortfolio(email) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCurrentStatus(email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        sme <- AdvisorPortfolioService.apply.getSme(email,smeOrgCode)
        results <- AdvisorPortfolioService.apply.getCurrentStatus(sme.get) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.status)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
