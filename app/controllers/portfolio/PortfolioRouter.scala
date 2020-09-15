package controllers.portfolio

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by Boniface Kabaso on 2016/09/24.
  */
class PortfolioRouter @Inject()
(advisorPortfolioController: AdvisorPortfolioController)
(fundManagerController: FundManagerController)
(fundPortfolioController: FundPortfolioController)
  extends SimpleRouter{
  override def routes: Routes = {

    case POST(p"/advisor/create") =>
      advisorPortfolioController.createOrUpdate
    case GET(p"/advisor/potfolio/current/$email") =>
      advisorPortfolioController.getCurrentPortfolio(email)
    case GET(p"/advisor/potfolio/old/$email") =>
      advisorPortfolioController.getOldPortfolio(email)
    case GET(p"/advisor/current/status/$email/$smeOrgCode") =>
      advisorPortfolioController.getCurrentStatus(email,smeOrgCode)
    case GET(p"/advisor/sme/get/$email/$smeOrgCode") =>
      advisorPortfolioController.getSme(email,smeOrgCode)
    case GET(p"/advisor/status/create") =>
      advisorPortfolioController.createStatus
    case GET(p"/advisor/status/$email/$smeOrgCode") =>
      advisorPortfolioController.getStatus(email,smeOrgCode)

    case POST(p"/fundmanager/create") =>
      fundManagerController.createOrUpdate
    case GET(p"/fundmanager/status/create") =>
      fundManagerController.createStatus
    case GET(p"/fundmanager/sme/all/$email") =>
      fundManagerController.getAllSmes(email)
    case GET(p"/fundmanager/sme/current/$fundOrgCode/$email") =>
      fundManagerController.getCurrentSmes(fundOrgCode,email)
    case GET(p"/fundmanager/sme/old/$fundOrgCode/$email") =>
      fundManagerController.getOldSmes(fundOrgCode,email)
    case GET(p"/fundmanager/current/status/$fundOrgCode/$email/$smeOrgCode") =>
      fundManagerController.getCurrentStatus(fundOrgCode,email,smeOrgCode)
    case GET(p"/fundmanager/sme/get/$fundOrgCode/$email/$smeOrgCode") =>
      fundManagerController.getSme(fundOrgCode,email,smeOrgCode)
    case GET(p"/fundmanager/status/$fundOrgCode/$email/$smeOrgCode") =>
      fundManagerController.getStatus(fundOrgCode,email,smeOrgCode)

    case POST(p"/fund/create") =>
      fundPortfolioController.createOrUpdate
    case GET(p"/fund/potfolio/current/$email") =>
      fundPortfolioController.getCurrentPortfolio(email)
    case GET(p"/fund/potfolio/old/$email") =>
      fundPortfolioController.getOldPortfolio(email)
    case GET(p"/fund/current/status/$email/$smeOrgCode") =>
      fundPortfolioController.getCurrentStatus(email,smeOrgCode)
    case GET(p"/fund/sme/get/$email/$smeOrgCode") =>
      fundPortfolioController.getSme(email,smeOrgCode)
    case GET(p"/fund/status/create") =>
      fundPortfolioController.createStatus
    case GET(p"/fund/status/$email/$smeOrgCode") =>
      fundPortfolioController.getStatus(email,smeOrgCode)
  }

}
