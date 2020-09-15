package services.util.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.util.Roles
import repositories.util.RoleRepository
import services.Service
import services.util.RoleService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/10.
  */
class RolesServiceImpl extends RoleService with Service{
  override def create(entity: Roles): Future[ResultSet] = {
    RoleRepository.save(entity)
  }

  override def getById(id: String): Future[Option[Roles]] = {
    RoleRepository.getById(id)
  }

  override def getAll: Future[Seq[Roles]] = {
    RoleRepository.getAll
  }
}
