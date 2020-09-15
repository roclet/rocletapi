package services.financials

import com.websudos.phantom.dsl._
import domain.financials.SettingUploads
import domain.financials.admin.AccountSystems
import repositories.financials.SettingUploadsRepository._
import services.financials.Impl.SettingUploadsServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/17.
  */
trait SettingUploadsService {
  def save(settinguploads:SettingUploads):Future[ResultSet]
  def getOrganisationSettingUpload(orgCode:String):Future[Option[SettingUploads]]
  def getAccountingSystemById(accountSystemsId:String):Future[Option[AccountSystems]]
}

object SettingUploadsService{
  def apply(): SettingUploadsService = new SettingUploadsServiceImpl()
}
