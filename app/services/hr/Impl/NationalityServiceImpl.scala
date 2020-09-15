package services.hr.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.hr.Nationality
import repositories.hr.NationalityRepository
import services.Service
import services.hr.NationalityService

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
class NationalityServiceImpl extends NationalityService with Service{
  override def save(nationality: Nationality): Future[ResultSet] = {
    NationalityRepository.save(nationality)
  }

  override def getNationalities: Future[Seq[Nationality]] = {
    NationalityRepository.getNationalities
  }

  override def getNationality(code: String): Future[Option[Nationality]] = {
    NationalityRepository.getNationality(code)
  }
}
