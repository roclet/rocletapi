package services.financials

import com.websudos.phantom.dsl._
import domain.financials.admin.AccountSystems
import services.financials.Impl.AccountSystemServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/10/15.
  */
trait AccountSystemService {
  def saveOrupdate(accountsystems: AccountSystems): Future[ResultSet]
  def getAccountingSystemById(accountSystemsId:String):Future[Option[AccountSystems]]
  def getAllAccountingSystems:Future[Seq[AccountSystems]]
}

object AccountSystemService{
  def apply: AccountSystemService = new AccountSystemServiceImpl()
}
