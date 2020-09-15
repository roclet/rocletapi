package controllers.people

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._
/**
  * Created by kuminga on 2016/09/24.
  */
class UsersRouter @Inject()
(useraddress:UserAddressControler)
(usercontact:UserContactController)
(userdemographic:UserDemographicsController)
(useridentities:UserIdentitiesController)
(userLogactivities:UserLogActivitiesController)
(userotp:UserOtpController)
(usertoken:UserTokenController)
(ownerInformationController: OwnerInformationController)
(user: UserController) extends SimpleRouter {
  override def routes: Routes = {

    case POST(p"/create") =>
      user.createNewUser
    case POST(p"/update") =>
      user.updateUser
    case GET(p"/user/$email") =>
      user.getUser(email)
    case GET(p"/organisation/$orgcode") =>
      user.getOrganisationUsers(orgcode)
    case GET(p"/email/$orgcode/$email") =>
      user.getUserByEmail(orgcode,email)
    case POST(p"/status/change") =>
      user.saveUserStatusChange
    case GET(p"/user/status/get/$orgCode") =>
      user.getUserStatusChangeorgCode(orgCode)
    case GET(p"/user/status/changes/$orgCode/$email") =>
      user.getAllUserStatusChange(orgCode,email)
    case POST(p"/user/role/create") =>
      user.saveUserRole
    case GET(p"/user/role/$orgCode/$email") =>
      user.getUserRolesByEmail(orgCode,email)
    case GET(p"/user/role/$orgCode") =>
      user.getUserRolesByorgCode(orgCode)
    case POST(p"/user/validate/pass") =>
      user.getCredentialValidation
    case POST(p"/user/update/pass") =>
      user.getUpdatePasswordStatus

    case POST(p"/address/create") =>
      useraddress.createOrUpdate
    case GET(p"/addresses/$orgcode") =>
      useraddress.getByOrg(orgcode)
    case GET(p"/address/$orgcode/$email") =>
      useraddress.getByEmail(orgcode,email)
    case GET(p"/address/$orgcode/$email/$id") =>
      useraddress.getById(orgcode,email,id)

    case POST(p"/contact/create") =>
      usercontact.createOrUpdate
    case GET(p"/contacts/$orgCode") =>
      usercontact.ByorgCode(orgCode)
    case GET(p"/contact/$orgCode/$email") =>
      usercontact.Byemail(orgCode,email)
    case GET(p"/contact/$orgCode/$email/$id") =>
      usercontact.Byid(orgCode,email,id)

    case POST(p"/demographics/create") =>
      userdemographic.createOrUpdate
    case GET(p"/demographics/org/$orgCode") =>
      userdemographic.ByOrgCode(orgCode)
    case GET(p"/demographics/emails/$orgCode/$email") =>
      userdemographic.Byemail(orgCode,email)


    case POST(p"/identities/create") =>
      useridentities.createOrUpdate
    case GET(p"/identities/$orgCode") =>
      useridentities.ByorgCode(orgCode)
    case GET(p"/identities/$orgCode/$email") =>
      useridentities.Byemail(orgCode,email)
    case GET(p"/identities/$orgCode/$email/$id") =>
      useridentities.ById(orgCode,email,id)

    case GET(p"/logs/$orgCode") =>
      userLogactivities.ByorgCode(orgCode)
    case GET(p"/logs/$orgCode/$email") =>
      userLogactivities.Byemail(orgCode,email)
    case GET(p"/logs/$orgCode/$email/$sessionid") =>
      userLogactivities.BySessionId(orgCode,email,sessionid)
    case GET(p"/logs/$orgCode/$email/$sessionid/$id") =>
      userLogactivities.ById(orgCode,email,sessionid,id)

    case POST(p"/otp/create") =>
      userotp.createUserOtp
    case GET(p"/otp/$orgCode/$email") =>
      userotp.getUserOtps(orgCode,email)
    case GET(p"/otp/$orgCode/$email/$userOtpId") =>
      userotp.getUserOtpByOtpId(orgCode,email,userOtpId)
    case GET(p"/current/otp/$orgCode/$email") =>
      userotp.getCurrentUserOtp(orgCode,email)
    case GET(p"/otp/event/$userOtpId/$id") =>
      userotp.getOtpEventById(userOtpId,id)
    case GET(p"/otp/event/$userOtpId") =>
      userotp.getOptEvents(userOtpId)

    case GET(p"/token/$orgCode/$email") =>
      usertoken.getUserSessions(orgCode,email)
    case GET(p"/current/token/$orgCode/$email") =>
      usertoken.getCurrentUserSession(orgCode,email)
    case GET(p"/user/session/$orgCode/$email/$userSessionId") =>
      usertoken.getUserSessionsById(orgCode,email,userSessionId)
    case GET(p"/token/events/$tokenId") =>
      usertoken.getTokenEvents(tokenId)
    case GET(p"/token/events/$tokenId/$id") =>
      usertoken.getTokenEvent(tokenId,id)

      // Owner Information
    case POST(p"/owner/info/save") =>
      ownerInformationController.saveOrupdate
    case GET(p"/owner/info/get")=>
      ownerInformationController.getOwnerInformation
  }

}
