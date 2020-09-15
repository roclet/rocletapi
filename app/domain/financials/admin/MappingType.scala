package domain.financials.admin

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/20.
  */
case class MappingType(
                      id:String,
                      name:String,
                      description:String
                      )
object MappingType{
  implicit val MappingTypeFmt = Json.format[MappingType]
}
