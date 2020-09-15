package services.financials.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.financials.OrganisationFinancialUploads
import repositories.financials.OrganisationFinancialUploadsRepository
import services.Service
import services.financials.OrganisationFinancialUploadsService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class OrganisationFinancialUploadsServiceImpl extends OrganisationFinancialUploadsService with Service {
  override def save(orgfinuploads: OrganisationFinancialUploads): Future[ResultSet] = {
    OrganisationFinancialUploadsRepository.save(orgfinuploads)
  }

  override def getOrganisationUploadedFiles(orgCode: String): Future[Seq[OrganisationFinancialUploads]] = {
    OrganisationFinancialUploadsRepository.getOrganisationUploadedFiles(orgCode)
  }

  override def getOrganisationUploadedFile(orgCode: String, filedId: String): Future[Option[OrganisationFinancialUploads]] = {
    OrganisationFinancialUploadsRepository.getOrganisationUploadedFile(orgCode,filedId)
  }

}
