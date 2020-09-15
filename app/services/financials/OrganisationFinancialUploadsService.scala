package services.financials

import com.websudos.phantom.dsl._
import domain.financials.OrganisationFinancialUploads
import services.financials.Impl.OrganisationFinancialUploadsServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
trait OrganisationFinancialUploadsService {

  def save(orgfinuploads: OrganisationFinancialUploads): Future[ResultSet]

  def getOrganisationUploadedFiles(orgCode: String): Future[Seq[OrganisationFinancialUploads]]

  def getOrganisationUploadedFile(orgCode: String, filedId: String): Future[Option[OrganisationFinancialUploads]]

}

object OrganisationFinancialUploadsService{
  def apply: OrganisationFinancialUploadsService = new OrganisationFinancialUploadsServiceImpl()
}
