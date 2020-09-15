package controllers.util

import conf.security.TokenCheck
import conf.util.MarginKeys
import domain.people.User
import domain.util.Mail
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.mail.{MailPasswordService, MailService}
import services.organisation.OrganisationTypeService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2015/11/28.
  */
class MailController extends Controller {

  def createOrUpdate = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Mail](input).get
      val results = MailService.apply.saveOrUpdate(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }

  def getById(org: String, id: String) = Action.async {
    request =>
      MailService.apply.get(MarginKeys.MAILORG, id) map (result =>
        Ok(Json.toJson(result)))
  }

  def sendEmail(user: User) = Action.async {
    request =>
      Future {
        MailPasswordService.apply.sendMail(user: User)
      } map (result =>
        Ok(Json.toJson("SENT")))
  }

  def resetAccount = Action.async(parse.json){
    request =>
      val input = request.body
      val entity = Json.fromJson[User](input).get
      Future {
        MailPasswordService.apply.resetAccount(entity)
      } map (result =>
        Ok(Json.toJson(entity)))
  }

  def getAll(org: String) = Action.async {
    request =>
      val response = for {
        auth <- TokenCheck.getTokenfromParam(request)
        results <- MailService.apply.getAll(MarginKeys.MAILORG) if auth.status == "VALID"
      } yield results
      response.map(ans => Ok(Json.toJson(ans)))
        .recover{ case e: Exception => Unauthorized }

  }

}
