package services.address.Impl

import java.util.concurrent.TimeUnit

import com.google.maps.{GeoApiContext, GeocodingApi}
import com.websudos.phantom.dsl._
import conf.util.MarginKeys
import domain.address.{Cordinates, GlobalLocation}
import repositories.address.GlobalLocationRepository
import services.Service
import services.address.GlobalLocationServices
import services.util.ApiKeysService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/11/03.
  */
class GlobalLocationServicesImpl extends GlobalLocationServices with Service{

  override def saveOrUpdate(location: GlobalLocation): Future[ResultSet] = {
    GlobalLocationRepository.save(location)

  }

  override def getLocationById(id: String): Future[Option[GlobalLocation]] = {
    GlobalLocationRepository.getLocationById(id)

  }

  override def getAllLocations: Future[Seq[GlobalLocation]] = {
    GlobalLocationRepository.getAllLocations

  }

  override def getCordinates(request: String): Future[Cordinates] = {
   val context =  for {
      apiKey <- ApiKeysService.apply.get(MarginKeys.GeocodingAPI)
    } yield {
      new GeoApiContext()
        .setQueryRateLimit(3)
        .setApiKey(apiKey.get.value)
        .setConnectTimeout(1, TimeUnit.SECONDS)
        .setReadTimeout(1, TimeUnit.SECONDS)
        .setWriteTimeout(1, TimeUnit.SECONDS)
    }
    val res = context map(cont=> GeocodingApi.newRequest(cont).address(request).await())

    res map ( results => Cordinates(results.head.geometry.location.lat,results.head.geometry.location.lng,
      results.head.formattedAddress))
  }
}
