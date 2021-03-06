package services.util

import com.datastax.driver.core.ResultSet
import domain.util.ApiKeys
import services.Service
import services.util.Impl.ApiKeysServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/03/19.
  */
trait ApiKeysService extends Service {
  def saveOrUpdate(entity: ApiKeys): Future[ResultSet]

  def get(id: String): Future[Option[ApiKeys]]

  def getAll: Future[Seq[ApiKeys]]
}

object ApiKeysService {
  def apply: ApiKeysService = new ApiKeysServiceImpl()
}