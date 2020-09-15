package domain.financials.admin

import java.util.Date

import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/10/15.
  */
case class CodeMappingSystem( accountSystemId:String,
                              id:String,
                              accountSystemName:String,
                              financialStatementType:String,
                              category:String,
                              subcategory:String,
                              startCode:Int,
                              endCode:Int,
                              sessionId:String,
                              date:Date
                            )
object CodeMappingSystem{
  implicit val IncomeStatCatFmt = Json.format[CodeMappingSystem]
}
