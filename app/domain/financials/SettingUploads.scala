package domain.financials

import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/08/19.
  */
case class SettingUploads(
                           orgCode: String,
                           mappingTypeId: String,
                           accountingSystem: String,
                           dateFormat: String,

                           codeColumn: Int,
                           descriptionColumn: Int,
                           debitColumn: Int,
                           creditColumn: Int,

                           startRow: Int,
                           dateRow: Int,
                           dateColumn: Int,
                           status: String,

                           sessionId: String) {
}

object SettingUploads {
  implicit val settinguploadsFmt = Json.format[SettingUploads]
}
