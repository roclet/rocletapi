package services.financials

import com.websudos.phantom.dsl._
import domain.financials.admin.CodeMappingSystem
import services.financials.Impl.CodeMappingSystemServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/10/15.
  */
trait  CodeMappingSystemService {
  def saveOrupdate(codemappingsystem: CodeMappingSystem): Future[ResultSet]
  def getCodeMappingSystemById(accountSystemId: String, id:String):Future[Option[CodeMappingSystem]]
  def getAllCodemappingSystems(accountSystemId: String):Future[Seq[CodeMappingSystem]]
  def  getAllCodeMapping:Future[Seq[CodeMappingSystem]]

}

object CodeMappingSystemService{
  def apply: CodeMappingSystemService = new CodeMappingSystemServiceImpl()
}


