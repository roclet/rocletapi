package services.util.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.util.Title
import repositories.util.TitleRepository
import services.Service
import services.util.TitleService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/10.
  */
class TitleServiceImpl extends TitleService with Service{
  override def create(entity: Title): Future[ResultSet] = {
    TitleRepository.save(entity)
  }

  override def getById(id: String): Future[Option[Title]] = {
    TitleRepository.getById(id)
  }

  override def getAll: Future[Seq[Title]] = {
    TitleRepository.getAll
  }
  override def truncateGender:Future[ResultSet]={
    TitleRepository.truncateGender()
  }
}
