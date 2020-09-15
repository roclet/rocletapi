package services.people

import com.websudos.phantom.dsl._
import domain.people.{UserOptEvent, UserOtp}
import services.people.Impl.UserOtpServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/09/08.
  */
trait UserOtpService {
  def saveUserOtp(userotp:UserOtp):Future[ResultSet]
  def getUserOtps(orgCode:String,email:String):Future[Seq[UserOtp]]
  def getUserOtpByOtpId(orgCode:String,email:String,userOtpId:String):Future[Option[UserOtp]]
  def getCurrentUserOtp(orgCode:String,email:String):Future[UserOtp]
  // Events Table
  def saveOtpEvent(useroptEvent:UserOptEvent):Future[ResultSet]
  def getOtpEventById(userOtpId:String,id:String):Future[Option[UserOptEvent]]
  def getOptEvents(UserOtpId:String):Future[Seq[UserOptEvent]]
}

object UserOtpService{
  def apply: UserOtpService = new UserOtpServiceImpl
}
