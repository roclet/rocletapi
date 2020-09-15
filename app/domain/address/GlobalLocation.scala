package domain.address

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/02/25.
  */
case class GlobalLocation(id:String,
                          name:String,
                          locationTypeId:String,
                          code:String,
                          latitude:String,
                          longitude:String,
                          parentId:String,
                          state:String,
                          date:Date)

object GlobalLocation{
  implicit val location = Json.format[GlobalLocation]
}
