package services.organisation.impl


import com.websudos.phantom.dsl._
import conf.security.AuthUtil
import conf.util.Util
import domain.organisation.OrganisationContact
import repositories.organisation.OrganisationContactRepository
import services.Service
import services.organisation.OrganisationContactService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
class OrganisationContactServiceImpl extends OrganisationContactService with Service{

  override def createOrupdate(organisationcontact:OrganisationContact):Future[ResultSet]={
    OrganisationContactRepository.save(organisationcontact)
  }
  override def findOrganisationContactByorgCode(orgCode:String):Future[Seq[OrganisationContact]]={
    OrganisationContactRepository.getOrganisationContactByorgCode(orgCode)
  }
  override def findOrganisationContactByemail(orgCode:String,email:String):Future[Seq[OrganisationContact]]={
    OrganisationContactRepository.getOrganisationContactByemail(orgCode,email)
  }
  override def findOrganisationContactById(orgCode:String,email:String,id:String):Future[Option[OrganisationContact]]={
    OrganisationContactRepository.getOrganisationContactById(orgCode,email,id)
  }
}
