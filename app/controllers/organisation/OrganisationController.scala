package controllers.organisation

import conf.security.TokenCheck
import conf.util.MarginKeys
import domain.organisation.{Organisation, OrganisationStatus}
import domain.portfolio.{AdvisorPortfolio, AdvisorPortfolioStatus}
import org.joda.time.DateTime
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.mail.MailPasswordService
import services.organisation.OrganisationServices
import services.people.UserService
import services.portfolio.AdvisorPortfolioService

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/11.
  */
class OrganisationController extends Controller {
  def createOrganisation = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Organisation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationServices.apply.createOrganisation(entity.copy(status = MarginKeys.INACTIVE)) if auth.status == "VALID"
        results <- OrganisationServices
          .apply.
          craeteOrganisationStatus(OrganisationStatus(entity.orgCode, new DateTime(), MarginKeys.INACTIVE, "CREATED OR UPDATED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def updateOrganisation = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Organisation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationServices.apply.updateOrganisation(entity) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def getOrganisationByCode(code: String) = Action.async {
    request =>

      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationServices.apply.getOrganisationByCode(code) //if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => {
            e.printStackTrace()
            Unauthorized}
        }
  }

  def getOrganisations(startval: Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationServices.apply.getOrganisations(startval) // if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.toList)))
        .recover { case e: Exception => Unauthorized }
  }


  def assignToAdvisor(smeOrgCode: String, advisorEmail: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        advisor <- UserService.apply().getUserByEmail(MarginKeys.MAILORG, advisorEmail) if auth.status == "VALID"
        sme <- OrganisationServices.apply().getOrganisationByCode(smeOrgCode)
        results <- AdvisorPortfolioService.apply.save(AdvisorPortfolio(advisorEmail, smeOrgCode))
        results <- AdvisorPortfolioService
          .apply.
          createStatus(AdvisorPortfolioStatus(advisorEmail, smeOrgCode, new DateTime(), MarginKeys.ALLOCATED))
        results <- OrganisationServices
          .apply.
          craeteOrganisationStatus(OrganisationStatus(sme.get.orgCode, new DateTime(), MarginKeys.ALLOCATED, "ASSIGNED TO ADVISOR"))
        email <- MailPasswordService.apply().mailAdvisor(advisor.get)
        results <-OrganisationServices.apply().updateOrganisation(sme.get.copy(status = MarginKeys.ALLOCATED))
      } yield sme
      response.map(ans => Ok(Json.toJson(ans)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def activateOrganisation = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Organisation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationServices.apply.activateOrganisation(entity.copy(status = MarginKeys.ACTIVE)) if auth.status == "VALID"
        results <- OrganisationServices
          .apply.
          craeteOrganisationStatus(OrganisationStatus(entity.orgCode, new DateTime(), MarginKeys.ACTIVE, "ACTIVATED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def enableOrganisation = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Organisation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationServices.apply.updateOrganisation(entity.copy(status = MarginKeys.ACTIVE)) if auth.status == "VALID"
        results <- OrganisationServices
          .apply.
          craeteOrganisationStatus(OrganisationStatus(entity.orgCode, new DateTime(), MarginKeys.ACTIVE, "ENABLED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }

  def disableOrganisation = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Organisation](input).get
      val response = for {
        auth <- TokenCheck.getToken(request)
        results <- OrganisationServices.apply.updateOrganisation(entity.copy(status = MarginKeys.DISABLED)) if auth.status == "VALID"
        results <- OrganisationServices
          .apply.
          craeteOrganisationStatus(OrganisationStatus(entity.orgCode, new DateTime(), MarginKeys.DISABLED, "DISABLED"))
      } yield results
      response.map(ans => Ok(Json.toJson(entity)))
        .recover {
          case e: Exception => Unauthorized
        }
  }


  def getActiveOrganisations(startval: Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationServices.apply.getOrganisations(startval) // if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.toList.filter(org => org.status equalsIgnoreCase MarginKeys.ACTIVE))))
        .recover { case e: Exception => Unauthorized }
  }

  def getInactiveOrganisations(startval: Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationServices.apply.getOrganisations(startval) // if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.toList.filter(org => org.status equalsIgnoreCase MarginKeys.INACTIVE))))
        .recover { case e: Exception => Unauthorized }
  }

  def getDisabledOrganisations(startval: Int) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- OrganisationServices.apply.getOrganisations(startval) // if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans.toList.filter(org => org.status equalsIgnoreCase MarginKeys.DISABLED))))
        .recover { case e: Exception => Unauthorized }
  }


}
