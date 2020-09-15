package repositories.hr

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.hr.Employee

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  * userId:String,
  * positionId:String,
  * nationality:String,
  * employeeCode:String,
  * status:String,
  * commencementDate:DateTime
  */
class EmployeeRepository extends CassandraTable[EmployeeRepository,Employee]{

  object userId extends StringColumn(this) with PartitionKey[String]
  object positionId extends StringColumn(this)
  object nationality extends StringColumn(this)
  object employeeCode extends StringColumn(this)
  object status extends StringColumn(this)
  object commencementDate extends DateTimeColumn(this)

  override def fromRow(r:Row): Employee ={
    Employee(
      userId(r),
      positionId(r),
      nationality(r),
      employeeCode(r),
      status(r) ,
      commencementDate(r)
    )
  }
}

object EmployeeRepository extends EmployeeRepository with RootConnector{

  override lazy val tableName = "employeedetails"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(employee:Employee):Future[ResultSet]={

    insert
      .value(_.userId,employee.userId)
      .value(_.positionId,employee.positionId)
      .value(_.nationality,employee.nationality)
      .value(_.employeeCode,employee.employeeCode)
      .value(_.status,employee.status)
      .value(_.commencementDate,employee.commencementDate)
      .future()
  }

  def getEmployeeDetails(userId:String):Future[Option[Employee]]={
    select.where(_.userId eqs userId).one()
  }

}