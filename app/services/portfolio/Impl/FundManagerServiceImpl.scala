package services.portfolio.Impl

import com.websudos.phantom.dsl.ResultSet
import conf.util.MarginKeys
import domain.portfolio.{FundManager, FundManagerStatus}
import repositories.portfolio.{FundManagerRepository, FundManagerStatusRepository}
import services.Service
import services.portfolio.FundManagerService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/11/12.
  */
class FundManagerServiceImpl extends FundManagerService with Service{
  override def save(fundmanager: FundManager): Future[ResultSet] = {
    FundManagerRepository.save(fundmanager)
  }

  override def getSme(fundOrgCode: String, email: String, smeOrgCode: String): Future[Option[FundManager]] = {
    FundManagerRepository.getSme(fundOrgCode,email,smeOrgCode)
  }

  override def getSmes(fundOrgCode: String, email: String): Future[Seq[FundManager]] = {
    FundManagerRepository.getSmes(fundOrgCode,email)
  }

  override def getAllSmes(fundOrgCode: String): Future[Seq[FundManager]] = {
    FundManagerRepository.getAllSmes(fundOrgCode)
  }

  override def createStatus(status: FundManagerStatus): Future[ResultSet] = {
    FundManagerStatusRepository.save(status)
  }

  override def getStatus(fundOrgCode: String, email: String, smeOrgCode: String): Future[Seq[FundManagerStatus]] = {
    FundManagerStatusRepository.getStatus(fundOrgCode,email,smeOrgCode)
  }

  override def getCurrentStatus(entity: FundManager): Future[FundManagerStatus] = {
    getStatus(entity.email,entity.email,entity.smeOrgCode) map ( status => status.head)
  }

  override def getOldSmes(fundOrgCode: String, email: String): Future[Seq[FundManager]] = {
    getSmes(fundOrgCode,email) map (va => va filter (portfolio => !filterOut(portfolio.getCurrentStatus)))
  }

  override def getCurrentSmes(fundOrgCode: String, email: String): Future[Seq[FundManager]] = {
    getSmes(fundOrgCode,email) map (va => va filter (portfolio => filterOut(portfolio.getCurrentStatus)))
  }

  def filterOut(currentStatus: Future[String]) = {
    val status = currentStatus map( status => status.equalsIgnoreCase(MarginKeys.ALLOCATED))
    Await.result(status,1.minutes)
  }
}
