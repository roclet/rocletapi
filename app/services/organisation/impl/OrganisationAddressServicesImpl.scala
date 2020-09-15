package services.organisation.impl

import com.websudos.phantom.dsl._
import conf.security.AuthUtil
import conf.util.Util
import domain.organisation.OrganisationAddress
import repositories.organisation.OrganisationAddressRepository
import services.Service
import services.organisation.OrganisationAddressServices

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/17.
  */
class OrganisationAddressServicesImpl extends OrganisationAddressServices with Service{



 override def createOrupdate(organisationaddress:OrganisationAddress):Future[ResultSet]={
   OrganisationAddressRepository.save(organisationaddress)
 }
  override def findOrgAddressByorgCode(orgCode:String):Future[Seq[OrganisationAddress]]={
    OrganisationAddressRepository.getOrgAddressByorgCode(orgCode)
  }
  override def findOrgAddressByemail(orgCode:String,email:String):Future[Seq[OrganisationAddress]]={
    OrganisationAddressRepository.getOrgAddressByemail(orgCode,email)
  }
  override def findOrgAddressById(orgCode:String,email:String,id:String):Future[Option[OrganisationAddress]]={
    OrganisationAddressRepository.getOrgAddressById(orgCode,email,id)
  }
}
