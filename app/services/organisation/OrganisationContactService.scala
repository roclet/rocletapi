package services.organisation


import com.websudos.phantom.dsl._
import domain.organisation.OrganisationContact
import services.organisation.impl.OrganisationContactServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/17.
  */
trait OrganisationContactService {
    def createOrupdate(organisationcontact:OrganisationContact):Future[ResultSet]
    def findOrganisationContactByorgCode(orgCode:String):Future[Seq[OrganisationContact]]
    def findOrganisationContactByemail(orgCode:String,email:String):Future[Seq[OrganisationContact]]
    def findOrganisationContactById(orgCode:String,email:String,id:String):Future[Option[OrganisationContact]]
}
object OrganisationContactService{
  def apply: OrganisationContactService = new OrganisationContactServiceImpl()
}
