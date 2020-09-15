package services.people.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.people.{UserOptEvent, UserOtp}
import repositories.people.{UserOptEventRepository, UserOtpRepository}
import services.Service
import services.people.UserOtpService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/09.
  */
class UserOtpServiceImpl extends UserOtpService with Service {
  override def saveUserOtp(userotp: UserOtp): Future[ResultSet] = {
    UserOtpRepository.save(userotp)
  }

  override def getUserOtps(orgCode:String,email: String): Future[Seq[UserOtp]] = {
    UserOtpRepository.getUserOtpByemail(orgCode,email)
  }

  override def getUserOtpByOtpId(orgCode:String,email: String, userOtpId: String): Future[Option[UserOtp]] = {
    UserOtpRepository.getUserOtpByuserOtpId(orgCode,email, userOtpId)
  }

  override def getCurrentUserOtp(orgCode:String,email: String): Future[UserOtp] = {
    UserOtpRepository.getUserOtpByemail(orgCode,email) map (otp =>
      otp.head)
  }

  // Events Table
  override def saveOtpEvent(useroptEvent: UserOptEvent): Future[ResultSet] = {
    UserOptEventRepository.save(useroptEvent)
  }

  override def getOtpEventById(userOtpId: String, id: String): Future[Option[UserOptEvent]] = {
    UserOptEventRepository.getOtpEventById(userOtpId, id)
  }

  override def getOptEvents(userOtpId: String): Future[Seq[UserOptEvent]] = {
    UserOptEventRepository.getByUserOptId(userOtpId)
  }


}
