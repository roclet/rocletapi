package services.util.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.util.ExternalRoles
import repositories.util.ExternalRolesRepository
import services.Service
import services.util.ExternalRolesService

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/03/04.
  */
class ExternalRolesServiceImpl extends ExternalRolesService with Service {

  override def create(entity: ExternalRoles): Future[ResultSet] = {
    ExternalRolesRepository.save(entity)
  }

  override def getById(id: String): Future[Option[ExternalRoles]] = {
    ExternalRolesRepository.getById(id)
  }

  override def getAll: Future[Seq[ExternalRoles]] = {
    
    ExternalRolesRepository.getAll
  }
}
