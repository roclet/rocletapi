package services.hr

import com.websudos.phantom.dsl.ResultSet
import domain.hr.{Employee, EmployeeDetails}
import domain.people.User
import services.hr.Impl.EmployeeServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
trait EmployeeService {

  def save(employee:Employee):Future[ResultSet]

  def getEmployeeDetails(userId:String):Future[Option[Employee]]

  def getAllEmployees(orgcode:String):Future[Seq[Option[User]]]

  def getOrganisationEmployees(orgcode:String):Future[Seq[EmployeeDetails]]

}

object EmployeeService{
  def apply: EmployeeService = new EmployeeServiceImpl()
}
