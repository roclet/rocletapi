package controllers.messages

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by kuminga on 2016/09/27.
  */
class MessageRouter @Inject()
(messages: MessagesController) extends SimpleRouter {
  override def routes: Routes = {
    case POST(p"/message/savemessage") =>
      messages.saveOrupedate

    case GET(p"/message/$orgCode") =>
      messages.getAll(orgCode)

//    case GET(p"/message/$orgCode/${int(date)}") =>
//      messages.getMessageDate(orgCode,date)
//    case GET(p"/message/$orgCode/${int(date)}/$id") =>
//      messages.getMessageById(orgCode,date,id)
  }
}
