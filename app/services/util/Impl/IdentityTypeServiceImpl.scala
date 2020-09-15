package services.util.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.util.IdentityTypes
import repositories.util.IdentityTypesRepository
import services.Service
import services.util.IdentityTypeService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/10.
  */
class IdentityTypeServiceImpl extends IdentityTypeService with  Service{
  override def create(entity: IdentityTypes): Future[ResultSet] = {
    IdentityTypesRepository.save(entity)
  }

  override def getById(id: String): Future[Option[IdentityTypes]] = {
    IdentityTypesRepository.getById(id)
  }

  override def getAll: Future[Seq[IdentityTypes]] = {
    IdentityTypesRepository.getAll
  }
}
