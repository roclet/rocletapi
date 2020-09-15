package services.organisation.impl

import com.websudos.phantom.dsl.ResultSet
import domain.organisation.Subsidize
import repositories.organisation.SubsidizeRepository
import services.Service
import services.organisation.SubsidizeService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/22.
  */
class SubsidizeServiceImpl extends SubsidizeService with Service{
  override def save(funders: Subsidize): Future[ResultSet]={
    SubsidizeRepository.save(funders)
  }
  override def getfindCode(findCode:String):Future[Seq[Subsidize]]={
    SubsidizeRepository.getfindCode(findCode)
  }
}
