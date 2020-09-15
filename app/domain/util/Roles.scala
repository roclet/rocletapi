package domain.util

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class Roles(
                id:String,
                rolename:String,
                privileges:Set[String]
                )
object Roles{
    implicit val rolesFmt = Json.format[Roles]
}
