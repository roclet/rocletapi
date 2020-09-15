package services.hr

import com.websudos.phantom.dsl.ResultSet
import domain.hr.Nationality
import services.hr.Impl.NationalityServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  */
trait NationalityService {

  def save(nationality: Nationality): Future[ResultSet]

  def getNationalities: Future[Seq[Nationality]]

  def getNationality(code: String): Future[Option[Nationality]]
}

object NationalityService{
  def apply: NationalityService = new NationalityServiceImpl()
}
