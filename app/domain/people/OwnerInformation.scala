package domain.people

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/04/06.
  */
case class OwnerInformation(userId:String, details:Map[String, String])

object OwnerInformation{

  implicit val ownerFmt = Json.format[OwnerInformation]
}
