package services.organisation

import com.websudos.phantom.dsl.ResultSet
import com.websudos.phantom.reactivestreams.Iteratee
import domain.financials.OrganisationBudget
import repositories.financials.OrganisationBudgetRepository.{insert, select}
import services.organisation.impl.OrganisationBudgetServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/01/10.
  */
trait OrganisationBudgetService {

  def save(budget: OrganisationBudget): Future[ResultSet]

  def getOrganisationBudgets(orgCode: String): Future[Seq[OrganisationBudget]]

  def getOrganisationBudgetByYear(orgCode: String, year: Int): Future[Seq[OrganisationBudget]]

  def getOrganisationBudgetItemByYear(orgCode: String, year: Int, itemCode: String): Future[Option[OrganisationBudget]]
}

object OrganisationBudgetService {
  def apply(): OrganisationBudgetService = new OrganisationBudgetServiceImpl()
}
