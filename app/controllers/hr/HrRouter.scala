package controllers.hr

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by hashcode on 2017/04/07.
  */
class HrRouter @Inject()
(employeeController: EmployeeController)
(nationalityController: NationalityController)
(positionController: PositionController)
 extends SimpleRouter {
  override def routes: Routes = {

    case GET(p"/employee/details/$userId") =>
      employeeController.getEmployeeDetails(userId)
    case POST(p"/employee/create") =>
      employeeController.createOrupdate
    case GET(p"/employee/all/$orgcode") =>
      employeeController.getAllEmployees(orgcode)


    case GET(p"/nationalities/all") =>
      nationalityController.getNationalities
    case POST(p"/nationality/create") =>
      nationalityController.createOrupdate
    case GET(p"/nationality/get/$code") =>
      nationalityController.getNationality(code)


    case GET(p"/positions/all") =>
      positionController.getPositions
    case GET(p"/position/get/$id") =>
      positionController.getPosition(id)
    case POST(p"/position/create") =>
      positionController.createOrupdate
  }
}
