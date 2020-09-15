package services.subscriptions

import com.datastax.driver.core.ResultSet
import domain.subscriptions.Subscriptions
import services.subscriptions.Impl.SubscriptionsServiceImpl

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/08.
  */
trait SubscriptionsService {
   def save(subscriptions:Subscriptions):Future[ResultSet]
   def getSubscriptionsid(id:String):Future[Option[Subscriptions]]

}
object SubscriptionsService{
  def apply: SubscriptionsService = new SubscriptionsServiceImpl()
}
