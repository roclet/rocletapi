package domain.organisation

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/13.
  */
case class UploadMetaData(email: String,
                          date: Date,
                          token: String,
                          orgCode: String,
                          url: String,
                          fileId:String
                         )

object UploadMetaData {
  implicit val uploadMetaDataFmt = Json.format[UploadMetaData]

}
