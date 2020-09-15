package controllers.util

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2016/09/23.
  */
class UtilRouter @Inject()
(mail: MailController)
(apikeys:ApiKeysController)
(gender:GenderController)
(roles:RolesController)
(idtypes:IdentityTypeController)
(title:TitleController)
(race:RaceController)
(status:StatusController)
(externalRolesController: ExternalRolesController)
(token: TokenController) extends SimpleRouter {
  override def routes: Routes = {
    case GET(p"/mail/$org") =>
      mail.getAll(org)
    case POST(p"/mail/create") =>
      mail.createOrUpdate
    case GET(p"/mail/get/$org/$id") =>
      mail.getById(org,id)
    case POST(p"/mail/reset") =>
      mail.resetAccount

    case POST(p"/createtoken") =>
      token.createNewToken
    case GET(p"/revokeToken/$tokenid") =>
      token.revoketoken(tokenid)
    case GET(p"/istokenvalid/$tokenid/$password") =>
      token.isTokenValid(tokenid, password)
    case GET(p"/hastokenexpired/$tokenid/") =>
      token.hasTokenExpired(tokenid)

    case GET(p"/apikeys/get/$id") =>
      apikeys.getById(id)
    case POST(p"/apikeys/create") =>
      apikeys.createOrUpdate
    case GET(p"/apikeys/all") =>
      apikeys.getAll
    case GET(p"/apikeys/company/key/" ? q"query=$name") =>
      apikeys.getCompanyKey(name)

    case GET(p"/race/get/$id") =>
      race.getById(id)
    case POST(p"/race/create") =>
      race.createOrUpdate
    case GET(p"/race/all") =>
      race.getAll


    case GET(p"/status/get/$id") =>
      status.getById(id)
    case POST(p"/status/create") =>
      status.createOrUpdate
    case GET(p"/status/all") =>
      status.getAll

    case GET(p"/gender/get/$id") =>
      gender.getById(id)
    case POST(p"/gender/create") =>
      gender.createOrUpdate
    case GET(p"/gender/all") =>
      gender.getAll

    case GET(p"/roles/get/$id") =>
      roles.getById(id)
    case POST(p"/roles/create") =>
      roles.createOrUpdate
    case GET(p"/roles/all") =>
      roles.getAll

      // External Controller

    case GET(p"/roles/external/get/$id") =>
      externalRolesController.getById(id)
    case POST(p"/roles/external/create") =>
      externalRolesController.createOrUpdate
    case GET(p"/roles/external/all") =>
      externalRolesController.getAll

    case GET(p"/idtypes/get/$id") =>
      idtypes.getById(id)
    case POST(p"/idtypes/create") =>
      idtypes.createOrUpdate
    case GET(p"/idtypes/all") =>
      idtypes.getAll


    case GET(p"/title/get/$id") =>
      title.getById(id)
    case POST(p"/title/create") =>
      title.createOrUpdate
    case GET(p"/title/all") =>
      title.getAll

    case GET(p"/title/truncatetitle") =>
      title.truncateTitle

  }
}
