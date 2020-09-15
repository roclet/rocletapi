package services.organisation

import com.websudos.phantom.dsl._
import domain.organisation.SubsidizeHistory
import services.organisation.impl.SubsidizeHistoryServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/22.
  */
trait SubsidizeHistoryService {
  def save(subsidizehistory:SubsidizeHistory):Future[ResultSet]
  def getbyfundeCode(funderCode:String):Future[Seq[SubsidizeHistory]]
  def getorgCode(funderCode:String,orgCode:String):Future[Option[SubsidizeHistory]]
}
object SubsidizeHistoryService{
  def apply: SubsidizeHistoryService = new SubsidizeHistoryServiceImpl()
}
