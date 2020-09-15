package controllers.syslogs

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by kuminga on 2016/09/28.
  */
class SyslogsRouter @Inject()
(syslogs: SyslogsController) extends SimpleRouter{
  override def routes: Routes = {
    case POST(p"/syslogs/savesyslogs") =>
      syslogs.savelogs

    case GET(p"/syslogs/$orgCode") =>
      syslogs.getOrganisationLogs(orgCode)

    case GET(p"/syslogs/$orgCode/$id") =>
      syslogs.getOrganisationLogEvent(orgCode,id)
  }

}
