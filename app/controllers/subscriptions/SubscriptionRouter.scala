package controllers.subscriptions

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by kuminga on 2016/10/01.
  */
class SubscriptionRouter @Inject()
(subscription: SubscriptionsController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/subscription/savesubscription") =>
      subscription.saveSubscription

    case GET(p"/subscription/$id") =>
      subscription.getSubscriptionsid(id)
  }
}
