package services.portfolio

import com.websudos.phantom.dsl._
import domain.portfolio.{AdvisorPortfolio, AdvisorPortfolioStatus}
import services.portfolio.Impl.AdvisorPortfolioServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */
trait AdvisorPortfolioService {
  def getOldPortfolio(email: String): Future[Seq[AdvisorPortfolio]]
  def getCurrentPortfolio(email: String): Future[Seq[AdvisorPortfolio]]
  def save(advisorPortfolio: AdvisorPortfolio): Future[ResultSet]
  def getSme(email: String, smeOrgCode: String): Future[Option[AdvisorPortfolio]]
  def getPortfolio(email: String): Future[Seq[AdvisorPortfolio]]
  def createStatus(advisorPortfolio: AdvisorPortfolioStatus): Future[ResultSet]
  def getStatus(email: String, smeOrgCode: String): Future[Seq[AdvisorPortfolioStatus]]
  def getCurrentStatus(entity:AdvisorPortfolio):  Future[AdvisorPortfolioStatus]
}

object AdvisorPortfolioService{
  def apply: AdvisorPortfolioService = new AdvisorPortfolioServiceImpl()
}
