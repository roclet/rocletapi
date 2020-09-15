package domain.financials

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by kuminga on 2016/08/19.
  */
case class ReferenceUploads(orgCode:String,
                            sessionId:String,
                            referenceId:String,
                            date:String,
                            login:String,
                            username:String,
                            fullname:String,
                            url:String,
                            uploadSettingsId:String) {

}
object ReferenceUploads{
    implicit val refuploadsFmt = Json.format[ReferenceUploads]
}