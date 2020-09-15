package services.portfolio

import com.websudos.phantom.dsl._
import domain.portfolio.{FundManager, FundManagerStatus}
import services.portfolio.Impl.FundManagerServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/12.
  */
trait FundManagerService {
  def getOldSmes(fundOrgCode: String, email: String): Future[Seq[FundManager]]
  def getCurrentSmes(fundOrgCode: String, email: String): Future[Seq[FundManager]]
  def save(location: FundManager): Future[ResultSet]
  def getSme(fundOrgCode:String, email:String,smeOrgCode:String):Future[Option[FundManager]]
  def getSmes(fundOrgCode:String, email:String) : Future[Seq[FundManager]]
  def getAllSmes(fundOrgCode:String) : Future[Seq[FundManager]]
  def createStatus(status: FundManagerStatus): Future[ResultSet]
  def getStatus(fundOrgCode: String, email: String,smeOrgCode: String): Future[Seq[FundManagerStatus]]
  def getCurrentStatus(entity:FundManager):  Future[FundManagerStatus]
}

object FundManagerService{
  def apply: FundManagerService = new FundManagerServiceImpl()
}
