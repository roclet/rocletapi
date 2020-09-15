package domain.reports

import play.api.libs.json.{Json, Writes}

/**
  * Created by hashcode on 2017/04/13.
  */
case class KpiIndicator(weigting: String,
                        map: Map[String, Map[String, String]])

object KpiIndicator {

  implicit val kpaWrites = new Writes[KpiIndicator] {
    def writes(kpi: KpiIndicator) = Json.obj(
      "weighting" -> kpi.weigting,
      "kpis" -> kpi.map
    )
  }
}
