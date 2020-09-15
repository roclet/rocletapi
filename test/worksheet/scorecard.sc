import play.api.libs.json.{JsValue, Json, Writes}

case class ScoreCardKPI(kpi:Map[String,Map[String,Int]],grade:String,score:Int)

implicit val scoreCardWrite = new Writes[ScoreCardKPI] {
  def writes(score: ScoreCardKPI) = Json.obj(
    "KPA"->score.kpi,
    "grade"->score.grade,
    "score"->score.score
  )
}

val profitability = Map("gross-profit"->1, "operating-profit"-> 1, "net-profit"-> 1, "slice"-> 1)

val cashflow=Map("free-cash-flow"->1, "net-cash-flow"-> 1, "cash-flow-margin"-> 1, "slice"-> 1)

val capital=Map("inventory-turns"->1, "working-capital"-> 1, "inventory-days"-> 1, "slice"-> 1)


val pkpi=ScoreCardKPI(Map("Profitability"->profitability,"Cash-Flow"->cashflow,"capital"->capital ),"C",10)




val json1: JsValue = Json.toJson(pkpi)
val compactJson: String = Json.stringify(json1)

println(compactJson)






