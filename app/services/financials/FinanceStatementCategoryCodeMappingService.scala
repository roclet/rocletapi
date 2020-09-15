package services.financials

import com.websudos.phantom.dsl._
import domain.financials.FinanceStatementCategoryCodeMapping
import domain.financials.admin.CodeMappingSystem
import domain.people.User
import services.financials.Impl.FinanceStatementCategoryCodeMappingServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/24.
  */
trait FinanceStatementCategoryCodeMappingService {

  def createOrupdate(financestatementcategory: FinanceStatementCategoryCodeMapping): Future[ResultSet]

  def loadCodeMappings(codeMappings: Seq[CodeMappingSystem], user: User): Future[String]

  def getCategoriesByFinType(orgCode: String, id: String): Future[Seq[FinanceStatementCategoryCodeMapping]]

  def getOrgCategories(orgCode: String): Future[Seq[FinanceStatementCategoryCodeMapping]]

  def getSubCategories(orgCode: String, financetype: String, category: String): Future[Seq[FinanceStatementCategoryCodeMapping]]
}

object FinanceStatementCategoryCodeMappingService {
  def apply: FinanceStatementCategoryCodeMappingService = new FinanceStatementCategoryCodeMappingServiceImpl()
}
