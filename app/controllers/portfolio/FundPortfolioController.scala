package controllers.portfolio

import conf.security.TokenCheck
import domain.portfolio.{FundPortfolio, FundPortfolioStatus}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.portfolio.FundPortfolioService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/11/12.
  */
class FundPortfolioController extends Controller {
  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FundPortfolio](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- FundPortfolioService.apply.save(entity) if auth.status == "VALID"
        results <-FundPortfolioService.apply.createStatus(FundPortfolioStatus(entity.fundOrgCode,entity.smeOrgCode,new DateTime(),"ASSIGNED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def createStatus = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[FundPortfolioStatus](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- FundPortfolioService.apply.createStatus(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getSme(email:String,smeOrgCode:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundPortfolioService.apply.getSme(email,smeOrgCode) if auth.status == "VALID"
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
        results <- FundPortfolioService.apply.getStatus(email,smeOrgCode) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getCurrentPortfolio(email:String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- FundPortfolioService.apply.getCurrentPortfolio(email) if auth.status == "VALID"
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
        results <- FundPortfolioService.apply.getOldPortfolio(email) if auth.status == "VALID"
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
        sme <- FundPortfolioService.apply.getSme(email,smeOrgCode)
        results <- FundPortfolioService.apply.getCurrentStatus(sme.get) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.status)))
        .recover {
          case e: Exception => Unauthorized
        }
  }
}
