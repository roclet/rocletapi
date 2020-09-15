package services.util

import com.websudos.phantom.dsl._
import domain.util.IdentityTypes
import services.util.Impl.IdentityTypeServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/10.
  */
trait IdentityTypeService {
  def create(entity: IdentityTypes): Future[ResultSet]

  def getById(id: String): Future[Option[IdentityTypes]]

  def getAll: Future[Seq[IdentityTypes]]
}

object IdentityTypeService {
  def apply: IdentityTypeService = new IdentityTypeServiceImpl()
}
