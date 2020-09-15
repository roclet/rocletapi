package services.util.Impl

import com.datastax.driver.core.ResultSet
import domain.util.Gender
import org.joda.time.DateTime
import repositories.util.GenderRepository
import services.Service
import services.util.GenderService
import scala.concurrent.Future

/**
  * Created by kuminga on 2016/10/01.
  */
class GenderServiceImpl extends GenderService with Service{
  override def save(gender:Gender):Future[ResultSet]={
    GenderRepository.save(gender)
  }
  override def findAll:Future[Seq[Gender]]={
    GenderRepository.getAll
  }
  override def findById(id:String):Future[Option[Gender]]={
    GenderRepository.getById(id)
  }
}
