package services.financials

import com.websudos.phantom.dsl.ResultSet
import domain.financials.FinancialApprovedStatement
import services.financials.Impl.FinancialApprovedStatementServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/12/19.
  */
trait FinancialApprovedStatementService {

  def save(statements: FinancialApprovedStatement): Future[ResultSet]

  def getOrganisationStatements(orgCode: String): Future[Seq[FinancialApprovedStatement]]

  def getOrganisationStatementsType(orgCode: String, statementType: String): Future[Seq[FinancialApprovedStatement]]

  def getOrganisationStatementsByCategory(orgCode: String, statementType: String, category: String): Future[Seq[FinancialApprovedStatement]]

}

object FinancialApprovedStatementService{

  def apply: FinancialApprovedStatementService = new FinancialApprovedStatementServiceImpl()
}
