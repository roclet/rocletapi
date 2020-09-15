package services.financials.Impl

import domain.financials.{FinancialApprovedStatement, FinancialStatementRecords}
import repositories.financials.{FinancialApprovedStatementRepository, FinancialStatementRecordsRepository}
import services.Service
import services.financials.FinancialReportsService

import scala.concurrent.Future


/**
  * Created by hashcode on 2016/11/08.
  */
class FinancialReportsServiceImpl extends FinancialReportsService with Service {
  override def getStatements(orgCode: String): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatements(orgCode)
  }

  override def getStatementsByType(orgCode: String, statementType: String): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatementsByType(orgCode, statementType)
  }

  override def getStatementsByCategory(orgCode: String, statementType: String, category: String): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatementsByCategory(orgCode, statementType, category)
  }

  override def getStatementsByYear(orgCode: String, statementType: String, category: String, year: Int): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatementsByYear(orgCode, statementType, category, year)
  }

  override def getStatementsByMonth(orgCode: String, statementType: String, category: String, year: Int, month: Int): Future[Seq[FinancialApprovedStatement]] = {
    FinancialApprovedStatementRepository.getStatementsByMonth(orgCode, statementType, category, year, month)
  }

  //  private def getResults(res: Future[Seq[FinancialStatementRecords]]): Future[FinancialStatementRecords] = {
  ////    res map (result => {
  ////      result.fold(
  ////        FinancialStatementRecords(
  ////          "",
  ////          "",
  ////          new Date(),
  ////          "",
  ////          "",
  ////          "",
  ////          BigDecimal(0),
  ////          0,
  ////          0,
  ////          new DateTime,
  ////          ""))(_ |+| _) })
  //  }
}
