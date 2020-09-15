package services.portfolio

import com.websudos.phantom.dsl._
import domain.portfolio.{FundPortfolio, FundPortfolioStatus}
import services.portfolio.Impl.FundPortfolioServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */
trait FundPortfolioService {
  def getOldPortfolio(email: String):Future[Seq[FundPortfolio]]

  def getCurrentPortfolio(email: String):Future[Seq[FundPortfolio]]

  def save(fund: FundPortfolio): Future[ResultSet]
  def getSme(fundOrgCode: String, smeOrgCode: String): Future[Option[FundPortfolio]]
  def getPortfolio(fundOrgCode: String): Future[Seq[FundPortfolio]]
  def createStatus(status: FundPortfolioStatus): Future[ResultSet]
  def getStatus(fundOrgCode: String, smeOrgCode: String): Future[Seq[FundPortfolioStatus]]
  def getCurrentStatus(entity:FundPortfolio): Future[FundPortfolioStatus]
}

object FundPortfolioService{
  def apply: FundPortfolioService = new FundPortfolioServiceImpl()
}
