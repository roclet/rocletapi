package domain.util

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class Gender (id:String,
                   name:String){

}
object Gender{
    implicit val genderFmt = Json.format[Gender]
}