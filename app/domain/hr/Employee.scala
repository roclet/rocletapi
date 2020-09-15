package domain.hr

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/04/06.
  */
case class Employee(userId:String,
                    positionId:String,
                    nationality:String,
                    employeeCode:String,
                    status:String,
                    commencementDate:DateTime)

object Employee{

  implicit val orgfmt= Json.format[Employee]

}
