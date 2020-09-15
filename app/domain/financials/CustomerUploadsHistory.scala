package domain.financials

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/12/17.
  */
case class CustomerUploadsHistory(orgCode: String,
                                  reference: String,
                                  date: DateTime,
                                  accountingCode: String,
                                  fileId: String,
                                  year: Int,
                                  month: Int,
                                  day: Option[Int],
                                  accountingSystem: String,
                                  debitValue: BigDecimal,
                                  creditValue: BigDecimal,
                                  entryCategory: String,
                                  entrySubCategory: String,
                                  entryDescription: String,
                                  txnType: String,
                                  csvStringInput: String,
                                  mappingCode: Option[String]
                                 )

object CustomerUploadsHistory {
  implicit val customerRejectedUploadsFmt = Json.format[CustomerUploadsHistory]
}

