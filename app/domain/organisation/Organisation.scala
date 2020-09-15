package domain.organisation

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class Organisation(orgCode:String,
                        name:String,
                        orgTypeId:String,
                        status:String,
                        contactEmail:String,
                        details:Map[String,String])
object Organisation{
    implicit val orgfmt= Json.format[Organisation]
}
