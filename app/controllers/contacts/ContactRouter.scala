package controllers.contacts

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by kuminga on 2016/09/24.
  */
class ContactRouter @Inject() (contacttype:ContactTypeController) extends SimpleRouter{
  override def routes: Routes = {
    case POST(p"/create") =>
      contacttype.createOrUpdate
    case GET(p"/get/$id") =>
      contacttype.getById(id)
    case GET(p"/all") =>
      contacttype.getAll
  }

}
