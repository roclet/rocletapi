package domain.reports

import play.api.libs.json.Json

/**
  * Created by hashcode on 2017/04/12.
  */
case class Weighting(orgcode: String,
                     kpa: String,
                     weighting: Double
                    )
object Weighting{
  implicit val weightingFmt = Json.format[Weighting]
  def default:Weighting =Weighting("","",0.00)

}
