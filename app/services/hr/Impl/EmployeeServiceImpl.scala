package services.hr.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.hr.{Employee, EmployeeDetails}
import domain.people._
import repositories.hr.EmployeeRepository
import services.Service
import services.hr.EmployeeService
import services.people._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
class EmployeeServiceImpl extends EmployeeService with Service {

  override def save(employee: Employee): Future[ResultSet] = {
    EmployeeRepository.save(employee)
  }

  override def getEmployeeDetails(userId: String): Future[Option[Employee]] = {
    EmployeeRepository.getEmployeeDetails(userId)
  }

  override def getAllEmployees(orgcode: String): Future[Seq[Option[User]]] = {
    val allEmployees = for {
      users <- UserService.apply().getOrganisationUsers(orgcode)

    } yield {
      val usersWhoAreEmployees = for (u <- users) yield EmployeeService.apply.getEmployeeDetails(u.email)
      val organisationEmployees = Future.sequence(usersWhoAreEmployees)

      val result = for {
        employees <- organisationEmployees
      } yield {
        employees map (employee => getEmployee(orgcode, employee))
      }

      val lete = result map (res => Future.sequence(res))

      lete.flatMap(answer => answer)

    }
    allEmployees.flatMap(result => result)

  }

  def getEmployee(orgCode: String, emp: Option[Employee]) = {
    emp match {
      case Some(employee) => UserService.apply().getUserByEmail(orgCode, employee.userId)
      case None => Future {
        None
      }
    }
  }

  override def getOrganisationEmployees(orgcode: String):Future[Seq[EmployeeDetails]] = {
     val results = for {
      employees <- UserService.apply().getOrganisationUsers(orgcode)
    } yield {
      employees map (user => {
         for{
           address <-UserAddressService.apply.findUserAddressByEmail(user.orgCode, user.email)
           contact <- UserContactService.apply.getAllUserContactByemail(user.orgCode, user.email)
           demographics <- UserDemographicsService.apply().getUserDemographicsByemail(user.orgCode, user.email)
           identites <- UserIdentitiesService.apply().getUserIdentitiesByemail(user.orgCode, user.email)
           roles <- UserService.apply().getUserRoles(user.orgCode, user.email)
           employeeDetails <-EmployeeService.apply.getEmployeeDetails(user.email)
         }yield {
           EmployeeDetails(user, address, contact, demographics, identites, roles, employeeDetails)
         }
      })
    }
    results flatMap ( me => Future.sequence(me))
  }
}
