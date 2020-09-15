package services.financials

import com.websudos.phantom.dsl._
import domain.financials.FinancialStatementRecords
import services.financials.Impl.FinancialStatementRecordsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
trait FinancialStatementRecordsService {
  def save(statements: FinancialStatementRecords): Future[ResultSet]

  def getOrganisationStatements(orgCode: String): Future[Seq[FinancialStatementRecords]]

  def getOrganisationStatementsType(orgCode: String, statementType: String): Future[Seq[FinancialStatementRecords]]

  def getOrganisationStatementsByCategory(orgCode: String, statementType: String, category: String): Future[Seq[FinancialStatementRecords]]

}

object FinancialStatementRecordsService{
  def apply: FinancialStatementRecordsService = new FinancialStatementRecordsServiceImpl()
}
