package services.financials.Impl
import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import domain.financials.admin.CodeMappingSystem
import repositories.financials.CodeMappingSystemRepository
import services.Service
import services.financials.CodeMappingSystemService

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/10/15.
  */
class CodeMappingSystemServiceImpl extends CodeMappingSystemService with Service{
  override def saveOrupdate(codemappingsystem:CodeMappingSystem):Future[ResultSet]={
    CodeMappingSystemRepository.save(codemappingsystem)
  }

  override def getCodeMappingSystemById(accountSystemId: String, id:String): Future[Option[CodeMappingSystem]] = {
    CodeMappingSystemRepository.getCodeMappingSystemById(accountSystemId, id)
  }

  override def getAllCodemappingSystems(accountSystemId: String): Future[Seq[CodeMappingSystem]] = {
    CodeMappingSystemRepository.getAllCodemappingSystems(accountSystemId)
  }

  override def getAllCodeMapping: Future[Seq[CodeMappingSystem]] = {
    CodeMappingSystemRepository.getAllCodemappingSystems
  }
}
