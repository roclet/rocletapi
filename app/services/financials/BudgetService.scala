package services.financials

import java.io.File

import com.websudos.phantom.dsl._
import domain.financials.Budget
import domain.organisation.UploadMetaData
import services.financials.Impl.BudgetServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/11/28.
  */
trait BudgetService {

  def save(budget: Budget): Future[ResultSet]

  def getOrganisationBudgets(orgCode: String): Future[Seq[Budget]]

  def getOrganisationBudgetByYear(orgCode: String, year: Int): Future[Seq[Budget]]

  def getItemsBudgetByMonth(orgCode: String, year: Int,month:Int): Future[Seq[Budget]]

  def getOrganisationBudgetItemByMonth(orgCode: String, year: Int, month:Int,item:String): Future[Option[Budget]]

  def processFile(file: File, data: UploadMetaData):Future[String]

}

object BudgetService {
  def apply: BudgetService = new BudgetServiceImpl()
}
