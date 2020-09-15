package services.financials.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.financials.SettingUploads
import domain.financials.admin.AccountSystems
import repositories.financials.{AccountSystemsRepository, SettingUploadsRepository}
import services.Service
import services.financials.SettingUploadsService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class SettingUploadsServiceImpl extends SettingUploadsService with Service{
  override def save(settinguploads:SettingUploads):Future[ResultSet]={
    SettingUploadsRepository.save(settinguploads)
  }

  override def getOrganisationSettingUpload(orgCode: String): Future[Option[SettingUploads]] = {
    SettingUploadsRepository.getOrganisationSettingUpload(orgCode)
  }

  override def getAccountingSystemById(accountSystemsId: String): Future[Option[AccountSystems]] = {
    AccountSystemsRepository.getAccountingSystemById(accountSystemsId)
  }
}
