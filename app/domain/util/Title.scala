package domain.util

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class Title(id:String,name:String) {

}

object Title{
    implicit val titleFmt = Json.format[Title]
}
