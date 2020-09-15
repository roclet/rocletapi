package services.util

import com.datastax.driver.core.ResultSet
import domain.util.Gender
import services.Service
import services.util.Impl.GenderServiceImpl
import domain.people.User
import scala.concurrent.Future
/**
  * Created by kuminga on 2016/10/01.
  */
trait GenderService {
  def save(gender: Gender): Future[ResultSet]
  def findById(id:String):Future[Option[Gender]]
  def findAll:Future[Seq[Gender]]
}
object GenderService{
  def apply: GenderService = new GenderServiceImpl()
}
