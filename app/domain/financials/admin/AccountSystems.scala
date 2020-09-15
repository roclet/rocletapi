package domain.financials.admin

import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/10/15.
  */
case class AccountSystems(
                     accountSystemsId:String,
                     accountingSystemName:String,
                     mappingTypeId:String,
                     dateFormat:String,
                     codeColumn:Int,
                     descriptionColumn:Int,
                     debitColumn:Int,
                     creditColumn:Int,
                     startRow:Int,
                     dateRow:Int,
                     dateColumn:Int,
                     status:String,
                     sessionId:String) {

}
object AccountSystems{
  implicit val accountsystemsFmt = Json.format[AccountSystems]
}

