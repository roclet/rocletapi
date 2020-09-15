import play.api.libs.json.{Json, _}


case class KPI(weigting:String, map:Map[String,Map[String,String]])

implicit val kpaWrites = new Writes[KPI] {
  def writes(kpi: KPI) = Json.obj(
    "weighting" -> kpi.weigting,
    "kpis" -> kpi.map
  )
}

val grossProfit = Map(
  "target" -> "60",
  "uom" -> "Percentage",
  "importance"->"High",
  "type"->"Above")

val operatingProfit = Map(
  "target" -> "60",
  "uom" -> "Percentage",
  "importance"->"High",
  "type"->"Above")

val netProfit = Map(
  "target" -> "60",
  "uom" -> "Percentage",
  "importance"->"High",
  "type"->"Above")

val profitabilityKpi =Map( "Gross Profit" -> grossProfit,
                        "Operating Profit" -> operatingProfit,
                        "Net Profit" -> netProfit)




val profitabilityKpa = KPI("30",profitabilityKpi)


 val KPAs = Map("Profitability"->profitabilityKpa)


val json1: JsValue = Json.toJson(KPAs)
val compactJson: String = Json.stringify(json1)

println(compactJson)
