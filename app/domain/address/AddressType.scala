package domain.address

import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class AddressType(id:String,name:String) {

}
object AddressType{
   implicit val addresstypeFmt = Json.format[AddressType]
}
