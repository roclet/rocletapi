package services.util.Impl

import com.datastax.driver.core.ResultSet
import domain.util.ApiKeys
import repositories.util.ApiKeysRepository
import services.Service
import services.util.ApiKeysService

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/15.
  */
class ApiKeysServiceImpl extends ApiKeysService with Service{


  def saveOrUpdate(entity: ApiKeys): Future[ResultSet] = {
    ApiKeysRepository.save(entity)
  }
  def get(id:String):Future[Option[ApiKeys]] ={

    ApiKeysRepository.getKeyById(id)
  }

  def getAll:Future[Seq[ApiKeys]] ={
    ApiKeysRepository.getAllkeys
  }

}
