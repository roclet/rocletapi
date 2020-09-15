package services.reports

import domain.reports.ScoreCard
import services.reports.Impl.ScoreCardServiceImpl

import scala.concurrent.Future

trait ScoreCardService {
  def getScoreCard(orgcode:String,period:String, year:Int,month:Int):Future[ScoreCard]
}

object ScoreCardService{
  def apply: ScoreCardService = new ScoreCardServiceImpl()
}
