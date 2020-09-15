package controllers.organisation

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by kuminga on 2016/09/25.
  */
class OrganisationRouter @Inject()
(organisation: OrganisationController)
(orgaddress: OrganisationAddressController)
(orgdocument: OrganisationDocumentController)
(orglocation: OrganisationLocationController)
(orgcontact: OrganisationContactController)
(organisationtype: OrganisationTypeController)

  extends SimpleRouter {
  override def routes: Routes = {


    case POST(p"/createorganisation") =>
      organisation.createOrganisation
    case POST(p"/update") =>
      organisation.updateOrganisation
    case GET(p"/code/$orgcode") =>
      organisation.getOrganisationByCode(orgcode)
    case GET(p"/all/${int(interval)}") =>
      organisation.getOrganisations(interval)
    case GET(p"/assign/$smeOrgCode/$advisorEmail") =>
      organisation.assignToAdvisor(smeOrgCode,advisorEmail)
    case POST(p"/activate") =>
      organisation.activateOrganisation
    case POST(p"/enable") =>
      organisation.enableOrganisation
    case POST(p"/disable") =>
      organisation.disableOrganisation
    case GET(p"/active/${int(interval)}") =>
      organisation.getActiveOrganisations(interval)
    case GET(p"/inactive/${int(interval)}") =>
      organisation.getInactiveOrganisations(interval)
    case GET(p"/disabled/${int(interval)}") =>
      organisation.getDisabledOrganisations(interval)



    case POST(p"/saveorgaddress") =>
      orgaddress.createOrupdate
    case GET(p"/orgaddress/$orgCode") =>
      orgaddress.getAll(orgCode)
    case GET(p"/orgaddress/$orgCode/$email") =>
      orgaddress.getByEmail(orgCode, email)
    case GET(p"/orgaddress/$orgCode/$email/$id") =>
      orgaddress.getById(orgCode, email, id)

    case POST(p"/saveorgdocument") =>
      orgdocument.createOrupdate
    case GET(p"/orgdocument/$orgCode") =>
      orgdocument.getDocByCode(orgCode)
    case GET(p"/orgdocument/$orgCode/$url") =>
      orgdocument.getDocById(orgCode, url)

    case POST(p"/location/create") =>
      orglocation.createOrupdate
    case GET(p"/location/history/$orgCode") =>
      orglocation.getLocations(orgCode)
    case GET(p"/location/get/$orgCode/${long(date)}") =>
      orglocation.getLocationById(orgCode, date)
    case GET(p"/location/current/$orgCode") =>
      orglocation.getCurrentLocation(orgCode)

    case POST(p"/saveorgcontact") =>
      orgcontact.createOrupdate
    case GET(p"/orgcontact/$orgCode") =>
      orgcontact.getByorgCode(orgCode)
    case GET(p"/orgcontact/$orgCode/$email") =>
      orgcontact.getByemail(orgCode, email)
    case GET(p"/orgcontact/$orgCode/$email/$id") =>
      orgcontact.getById(orgCode, email, id)


    case POST(p"/saveorganisationtype") =>
      organisationtype.createOrupdate
    case GET(p"/organisationtype/type") =>
      organisationtype.getAllOrganisationType
    case GET(p"/organisationtype/$id") =>
      organisationtype.getById(id)

  }
}
