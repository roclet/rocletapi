package controllers

import javax.inject._

import play.api._
import play.api.mvc.{Action, Controller}
import services.setup.SetupService
import scala.concurrent.ExecutionContext.Implicits.global
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject() extends Controller {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  /*def setup = Action.async{
    request =>
      SetupService.runSetUp map ( res =>
        Ok("Created "))
  }*/

  def setup = Action.async{
    request =>
      SetupService.runSetUp map ( res =>
        Ok("Created "))
  }
}
