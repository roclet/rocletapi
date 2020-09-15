package domain.financials

import java.util.Date

import play.api.libs.json.Json

import scalaz.Monoid

/**
  * Created by hashcode on 2016/08/20.
  */
case class OrganisationTarget(
                             orgCode:String,
                             id:String,
                             description:String,
                             targetValue: BigDecimal,
                             date:Date
                             )
object OrganisationTarget {
  implicit val orgTarget = Json.format[OrganisationTarget]
  implicit val monoid: Monoid[OrganisationTarget] = new Monoid[OrganisationTarget]{
     def append(value1: OrganisationTarget, value2: => OrganisationTarget)=
       OrganisationTarget(
         value1.orgCode,
         value1.id,
         value1.description,
         value1.targetValue+value2.targetValue,
         new Date)

    def zero: OrganisationTarget =  OrganisationTarget(
      "",
      "",
      "",
      BigDecimal("0"),
      new Date())
  }

}
