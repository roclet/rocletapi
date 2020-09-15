package services.portfolio.Impl

import com.websudos.phantom.dsl.ResultSet
import conf.util.MarginKeys
import domain.portfolio.{AdvisorPortfolio, AdvisorPortfolioStatus}
import repositories.portfolio.{AdvisorPortfolioRepository, AdvisorPortfolioStatusRepository}
import services.Service
import services.portfolio.AdvisorPortfolioService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/11/12.
  */
class AdvisorPortfolioServiceImpl extends AdvisorPortfolioService with Service {
  override def save(advisorPortfolio: AdvisorPortfolio): Future[ResultSet] = {
    AdvisorPortfolioRepository.save(advisorPortfolio)
  }

  override def getSme(email: String, smeOrgCode: String): Future[Option[AdvisorPortfolio]] = {
    AdvisorPortfolioRepository.getSme(email, smeOrgCode)
  }

  override def getPortfolio(email: String): Future[Seq[AdvisorPortfolio]] = {
    AdvisorPortfolioRepository.getPortfolio(email)
  }

  override def createStatus(advisorPortfolio: AdvisorPortfolioStatus): Future[ResultSet] = {
    AdvisorPortfolioStatusRepository.save(advisorPortfolio)
  }

  override def getStatus(email: String, smeOrgCode: String): Future[Seq[AdvisorPortfolioStatus]] = {
    AdvisorPortfolioStatusRepository.getStatus(email, smeOrgCode)
  }

  override def getCurrentStatus(entity: AdvisorPortfolio): Future[AdvisorPortfolioStatus] = {
    getStatus(entity.email, entity.smeOrgCode) map (status => status.head)
  }

  override def getOldPortfolio(email: String): Future[Seq[AdvisorPortfolio]] = {
    getPortfolio(email) map (va => va filter (portfolio => !filterOut(portfolio.getCurrentStatus)))
  }

  override def getCurrentPortfolio(email: String): Future[Seq[AdvisorPortfolio]] = {
    getPortfolio(email) map (va => va filter (portfolio => filterOut(portfolio.getCurrentStatus)))
  }

  def filterOut(currentStatus: Future[String]) = {
    val status = currentStatus map( status => status.equalsIgnoreCase(MarginKeys.ALLOCATED))
    Await.result(status,1.minutes)
  }
}
