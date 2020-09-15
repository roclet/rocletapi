package services.financials

import com.websudos.phantom.reactivestreams._
import domain.financials.{FinancialApprovedStatement, FinancialStatementRecords}
import repositories.financials.FinancialStatementRecordsRepository._
import services.financials.Impl.FinancialReportsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/08.
  */
trait FinancialReportsService {

  def getStatements(orgCode: String): Future[Seq[FinancialApprovedStatement]]

  def getStatementsByType(orgCode: String, statementType: String): Future[Seq[FinancialApprovedStatement]]

  def getStatementsByCategory(orgCode: String, statementType: String, category: String): Future[Seq[FinancialApprovedStatement]]

  def getStatementsByYear(orgCode: String, statementType: String, category: String, year: Int): Future[Seq[FinancialApprovedStatement]]

  def getStatementsByMonth(orgCode: String, statementType: String, category: String, year: Int, month: Int): Future[Seq[FinancialApprovedStatement]]
}

object FinancialReportsService {
  def apply: FinancialReportsService = new FinancialReportsServiceImpl()

}
