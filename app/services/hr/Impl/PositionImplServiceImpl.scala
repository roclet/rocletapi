package services.hr.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.hr.Position
import repositories.hr.PositionRepository
import services.Service
import services.hr.PositionService

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
class PositionImplServiceImpl extends PositionService with Service{

  override def save(position: Position): Future[ResultSet] = {
    PositionRepository.save(position)
  }

  override def getPositions: Future[Seq[Position]] = {
    PositionRepository.getPositions
  }

  override def getPosition(positionId: String): Future[Option[Position]] = {
    PositionRepository.getPosition(positionId)
  }
}
