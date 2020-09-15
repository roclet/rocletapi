package services.financials.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.financials.FinancialStatementRecords
import repositories.financials.FinancialStatementRecordsRepository
import services.Service
import services.financials.FinancialStatementRecordsService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class FinancialStatementRecordsServiceImpl extends FinancialStatementRecordsService with Service{
  override def save(statements: FinancialStatementRecords): Future[ResultSet] = {
    FinancialStatementRecordsRepository.save(statements)
  }

  override def getOrganisationStatements(orgCode: String): Future[Seq[FinancialStatementRecords]] = {
    FinancialStatementRecordsRepository.getStatements(orgCode)
  }

  override def getOrganisationStatementsType(orgCode: String, statementType: String): Future[Seq[FinancialStatementRecords]] = {
    FinancialStatementRecordsRepository.getStatementsByType(orgCode,statementType)
  }

  override def getOrganisationStatementsByCategory(orgCode: String, statementType: String, category: String): Future[Seq[FinancialStatementRecords]] = {
    FinancialStatementRecordsRepository.getStatementsByCategory(orgCode,statementType,category)
  }


}
