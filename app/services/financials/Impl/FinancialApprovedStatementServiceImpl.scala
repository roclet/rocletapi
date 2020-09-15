package services.financials.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.financials.FinancialApprovedStatement
import repositories.financials.FinancialApprovedStatementRepository
import services.Service
import services.financials.FinancialApprovedStatementService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/19.
  */
class FinancialApprovedStatementServiceImpl extends FinancialApprovedStatementService with Service {

  override def save(statements: FinancialApprovedStatement): Future[ResultSet] = {
    FinancialApprovedStatementRepository.save(statements)
  }

  override def getOrganisationStatements(orgCode: String): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatements(orgCode)
  }

  override def getOrganisationStatementsType(orgCode: String, statementType: String): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatementsByType(orgCode, statementType)
  }

  override def getOrganisationStatementsByCategory(orgCode: String, statementType: String, category: String): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatementsByCategory(orgCode, statementType, category)
  }

}
