package services.util


import com.datastax.driver.core.ResultSet
import com.websudos.phantom.dsl.ResultSet
import domain.util.Title
import services.util.Impl.TitleServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/10.
  */
trait TitleService {

  def create(entity: Title): Future[ResultSet]

  def getById(id: String): Future[Option[Title]]

  def getAll: Future[Seq[Title]]
  def truncateGender:Future[ResultSet]
}

object TitleService{
  def apply: TitleService = new TitleServiceImpl()
}
