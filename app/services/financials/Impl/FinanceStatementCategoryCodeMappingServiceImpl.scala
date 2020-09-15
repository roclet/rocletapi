package services.financials.Impl


import com.websudos.phantom.dsl._
import domain.financials.FinanceStatementCategoryCodeMapping
import domain.financials.admin.CodeMappingSystem
import domain.people.User
import repositories.financials.FinanceStatementCategoryCodeMappingRepository
import services.Service
import services.financials.FinanceStatementCategoryCodeMappingService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/24.FIX FIX FIX
  */
@Deprecated
class FinanceStatementCategoryCodeMappingServiceImpl extends FinanceStatementCategoryCodeMappingService with Service{

  override def createOrupdate(financestatementcategory:FinanceStatementCategoryCodeMapping):Future[ResultSet]={
    FinanceStatementCategoryCodeMappingRepository.save(financestatementcategory)
  }
  override def getCategoriesByFinType(orgCode:String, id:String):Future[Seq[FinanceStatementCategoryCodeMapping]]={
    FinanceStatementCategoryCodeMappingRepository.getCategoriesByFinType(orgCode,id)
  }
  def getOrgCategories(orgCode:String):Future[Seq[FinanceStatementCategoryCodeMapping]]={
    FinanceStatementCategoryCodeMappingRepository.getOrgCategories(orgCode)
  }
// Needs Ana;ysis
  override def loadCodeMappings(codeMappings: Seq[CodeMappingSystem], user: User): Future[String] = {

  codeMappings foreach( code => {
      val codeMap = FinanceStatementCategoryCodeMapping(
        user.orgCode,
        code.accountSystemId,
        code.financialStatementType,
        code.category,
        code.subcategory,
        code.startCode,
        code.endCode,
        code.sessionId,
        code.date)
      createOrupdate(codeMap)
    })

    Future{ "NOT SURE "}
  }

  override def getSubCategories(orgCode: String, financetype: String, category: String): Future[Seq[FinanceStatementCategoryCodeMapping]] = {
    FinanceStatementCategoryCodeMappingRepository.getSubCategories(orgCode,financetype,category)
  }
}
