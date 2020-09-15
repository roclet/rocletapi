package services.financials

import com.websudos.phantom.dsl._
import domain.financials.admin.MappingType
import services.financials.Impl.MappingTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/17.
  */
trait MappingTypeService {
   def createOrupdate(mappingtype:MappingType):Future[ResultSet]
   def getMappingTypeById(id:String):Future[Option[MappingType]]
   def findAll:Future[Seq[MappingType]]
}
object MappingTypeService{
   def apply(): MappingTypeService = new MappingTypeServiceImpl()
}
