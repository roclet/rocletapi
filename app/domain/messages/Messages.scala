package domain.messages

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class Messages(orgCode: String,
                    id: String,
                    messageType: String,
                    messageBody: String,
                    messageSystemResponse: String,
                    messageStatus: String,
                    date:DateTime
                   ) {

}
object Messages{
   implicit val messagesFmt=Json.format[Messages]
}