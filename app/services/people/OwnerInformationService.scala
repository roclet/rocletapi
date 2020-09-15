package services.people

import domain.people.OwnerInformation
import services.people.Impl.OwnerInformationServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
trait OwnerInformationService {

  def save(orgCode: String, employee: OwnerInformation): Future[String]

  def getOwnerInformation(orgCode:String): Future[OwnerInformation]
}

object OwnerInformationService{

  def apply: OwnerInformationService = new OwnerInformationServiceImpl()
}
