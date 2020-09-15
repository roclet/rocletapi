package services.subscriptions.Impl

import com.datastax.driver.core.ResultSet
import domain.subscriptions.Subscriptions
import org.joda.time.DateTime
import repositories.subscriptions.SubscriptionsRepository
import services.Service
import services.subscriptions.SubscriptionsService
import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/08.
  */
class SubscriptionsServiceImpl extends SubscriptionsService with Service{
     override def save(subscriptions:Subscriptions):Future[ResultSet]={
       SubscriptionsRepository.save(subscriptions)
     }
     override def getSubscriptionsid(id:String):Future[Option[Subscriptions]]={
       SubscriptionsRepository.getSubscriptionsid(id)
     }
}
