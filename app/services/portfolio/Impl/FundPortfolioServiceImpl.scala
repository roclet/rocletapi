package services.portfolio.Impl

import com.websudos.phantom.dsl.ResultSet
import conf.util.MarginKeys
import domain.portfolio.{FundPortfolio, FundPortfolioStatus}
import repositories.portfolio.{FundPortfolioRepository, FundPortfolioStatusRepository}
import services.Service
import services.portfolio.{AdvisorPortfolioService, FundPortfolioService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/11/12.
  */
class FundPortfolioServiceImpl extends FundPortfolioService with Service{
  override def save(fund: FundPortfolio): Future[ResultSet] = {
    FundPortfolioRepository.save(fund)
  }

  override def getSme(fundOrgCode: String, smeOrgCode: String): Future[Option[FundPortfolio]] = {
    FundPortfolioRepository.getSme(fundOrgCode,smeOrgCode)
  }

  override def getPortfolio(fundOrgCode: String): Future[Seq[FundPortfolio]] = {
    FundPortfolioRepository.getPortfolio(fundOrgCode)
  }

  override def createStatus(status: FundPortfolioStatus): Future[ResultSet] = {
    FundPortfolioStatusRepository.save(status)
  }

  override def getStatus(fundOrgCode: String, smeOrgCode: String): Future[Seq[FundPortfolioStatus]] = {
    FundPortfolioStatusRepository.getStatus(fundOrgCode,smeOrgCode)
  }

  override def getCurrentStatus(entity: FundPortfolio): Future[FundPortfolioStatus] = {
    getStatus(entity.fundOrgCode,entity.smeOrgCode) map ( status => status.head)
  }

  override def getOldPortfolio(email: String): Future[Seq[FundPortfolio]] = {
    getPortfolio(email) map (va => va filter (portfolio => !filterOut(portfolio.getCurrentStatus)))
  }

  override def getCurrentPortfolio(email: String): Future[Seq[FundPortfolio]] = {
    getPortfolio(email) map (va => va filter (portfolio => filterOut(portfolio.getCurrentStatus)))
  }

  def filterOut(currentStatus: Future[String]) = {
    val status = currentStatus map( status => status.equalsIgnoreCase(MarginKeys.ACTIVE))
    Await.result(status,1.minutes)
  }
}
