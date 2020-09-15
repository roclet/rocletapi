package services.financials.Impl

import com.websudos.phantom.dsl._
import domain.financials.admin.MappingType
import repositories.financials.MappingTypeRepository
import services.Service
import services.financials.MappingTypeService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class MappingTypeServiceImpl extends MappingTypeService with Service{


  override def createOrupdate(mappingtype:MappingType):Future[ResultSet]={
    MappingTypeRepository.save(mappingtype)
  }
  override def getMappingTypeById(id:String):Future[Option[MappingType]]={
    MappingTypeRepository.getMappingTypeById(id)
  }
  def findAll:Future[Seq[MappingType]]={
    MappingTypeRepository.findAll
  }

}
