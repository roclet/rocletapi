package domain.financials

import play.api.libs.json.Json

/**
  * Created by kuminga on 2016/08/30.
  */
case class SettingOfFinanceStatement(id:String,
                                     name:String,
                                     startcode:String,
                                     endcode:String,
                                     orgCode:String,
                                     systemaccount:String) {

}
object SettingOfFinanceStatement{
  implicit val settingofFinancestatement = Json.format[SettingOfFinanceStatement]
}