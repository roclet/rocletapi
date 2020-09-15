package services.organisation


import com.websudos.phantom.dsl._
import domain.organisation.Subsidize
import services.organisation.impl.SubsidizeServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/22.
  */
trait SubsidizeService {
  def save(subsidize: Subsidize): Future[ResultSet]
  def getfindCode(findCode:String):Future[Seq[Subsidize]]
}
object SubsidizeService{
  def apply: SubsidizeService = new SubsidizeServiceImpl()
}