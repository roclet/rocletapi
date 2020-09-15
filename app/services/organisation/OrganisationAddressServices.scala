package services.organisation

import com.websudos.phantom.dsl._
import domain.organisation.{OrganisationLocation, OrganisationAddress}
import services.organisation.impl.OrganisationAddressServicesImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
trait OrganisationAddressServices {
  def createOrupdate(organisationaddress:OrganisationAddress):Future[ResultSet]
  def findOrgAddressByorgCode(orgCode:String):Future[Seq[OrganisationAddress]]
  def findOrgAddressByemail(orgCode:String,email:String):Future[Seq[OrganisationAddress]]
  def findOrgAddressById(orgCode:String,email:String,id:String):Future[Option[OrganisationAddress]]
}
object OrganisationAddressServices{
  def apply(): OrganisationAddressServices = new OrganisationAddressServicesImpl()
}
