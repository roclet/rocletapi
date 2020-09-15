package controllers.address

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2016/09/23.
  */
class AddressRouter @Inject()
(addressType: AddressTypeController)
(globalLocationController: GlobalLocationController)
(locationType: LocationTypeController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/all") =>
      addressType.getAll
    case GET(p"/$id") =>
      addressType.getById(id)
    case POST(p"/create") =>
      addressType.createOrUpdate
    case GET(p"/location/all") =>
      locationType.getAll
    case GET(p"/location/$id") =>
      locationType.getById(id)
    case POST(p"/location/create") =>
      locationType.createOrUpdate
    case GET(p"/global/all") =>
      globalLocationController.getAll
    case GET(p"/global/$id") =>
      globalLocationController.getById(id)
    case POST(p"/global/create") =>
      globalLocationController.createOrUpdate

    case POST(p"/global/cordinates") =>
      globalLocationController.getCordinates
  }
}
