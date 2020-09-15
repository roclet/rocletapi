package domain.people

import play.api.libs.json.Json

case class Login(orgCode:String, orgName:String, email:String) {
  override def hashCode(): Int = {
    val prime = 31
    var result = 1
    result = prime * result + orgCode.hashCode
    result
  }
  override def equals(that: scala.Any): Boolean = that match {
    case that: Login =>
      that.canEqual(this) && this.hashCode == that.hashCode
    case _ => false
  }
}

object Login {

  implicit val loginFmt = Json.format[Login]
  def identity: Login = Login("","","")

}
