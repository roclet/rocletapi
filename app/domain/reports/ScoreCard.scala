package domain.reports

import play.api.libs.json.{Json, Writes}

case class ScoreCard(kpa: Map[String, Map[String, Int]], grade: String, score: Double)

object ScoreCard {
  implicit val scoreCardWrite = new Writes[ScoreCard] {
    def writes(score: ScoreCard) = Json.obj(
      "KPA" -> score.kpa,
      "grade" -> score.grade,
      "score" -> score.score
    )
  }
}
