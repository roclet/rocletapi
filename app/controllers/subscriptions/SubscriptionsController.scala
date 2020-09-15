package controllers.subscriptions

import domain.subscriptions.Subscriptions
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.subscriptions.SubscriptionsService
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by hashcode on 2016/09/18.
  */
class SubscriptionsController extends Controller{
  def saveSubscription = Action.async(parse.json) {
    request =>
      val input = request.body
      val entity = Json.fromJson[Subscriptions](input).get
      val results = SubscriptionsService.apply.save(entity)
      results.map(result =>
        Ok(Json.toJson(entity)))
  }
  def getSubscriptionsid(id:String) = Action.async {
    request =>
      SubscriptionsService.apply.getSubscriptionsid(id) map (result =>
        Ok(Json.toJson(result)))
  }
}
