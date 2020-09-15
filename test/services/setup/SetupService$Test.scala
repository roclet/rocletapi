package services.setup

import org.scalatest.FunSuite
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/09/23.
  */
class SetupService$Test extends FunSuite {

  test("testRunSetUp") {
    val result = Await.result(SetupService.runSetUp, 2.minutes)

  }

}
