package services.financials.Impl

import com.websudos.phantom.dsl._
import domain.financials.admin.AccountSystems
import repositories.financials.AccountSystemsRepository
import services.Service
import services.financials.AccountSystemService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/10/15.
  */
class AccountSystemServiceImpl extends AccountSystemService with Service{
  override def saveOrupdate(accountsystems: AccountSystems): Future[ResultSet]={

    AccountSystemsRepository.save(accountsystems)
  }

  override def getAccountingSystemById(accountSystemsId: String): Future[Option[AccountSystems]] = {
    AccountSystemsRepository.getAccountingSystemById(accountSystemsId)
  }

  override def getAllAccountingSystems: Future[Seq[AccountSystems]] = {
    AccountSystemsRepository.getAllAccountingSystems
  }
}
