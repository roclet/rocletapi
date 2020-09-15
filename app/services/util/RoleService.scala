package services.util

import com.websudos.phantom.dsl.ResultSet
import domain.util.Roles
import services.util.Impl.RolesServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/10/01.
  */
trait RoleService {
  def create(entity: Roles): Future[ResultSet]

  def getById(id: String): Future[Option[Roles]]

  def getAll: Future[Seq[Roles]]
}

object RoleService{
  def apply: RoleService = new RolesServiceImpl()
}
