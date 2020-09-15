package services.hr

import com.websudos.phantom.dsl.ResultSet
import domain.hr.Position
import services.hr.Impl.PositionImplServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
trait PositionService {

  def save(position: Position): Future[ResultSet]

  def getPositions: Future[Seq[Position]]

  def getPosition(positionId: String): Future[Option[Position]]
}

object PositionService{
  def apply: PositionService = new PositionImplServiceImpl()
}
