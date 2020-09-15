package services.util

import com.websudos.phantom.dsl.ResultSet
import domain.util.ExternalRoles
import services.util.Impl.ExternalRolesServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/03/04.
  */
trait ExternalRolesService {

  def create(entity: ExternalRoles): Future[ResultSet]

  def getById(id: String): Future[Option[ExternalRoles]]

  def getAll: Future[Seq[ExternalRoles]]
}

object ExternalRolesService{
  def apply: ExternalRolesService = new ExternalRolesServiceImpl()
}
