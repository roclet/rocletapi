package services.organisation.impl

import com.websudos.phantom.dsl.ResultSet
import domain.financials.OrganisationBudget
import repositories.financials.OrganisationBudgetRepository
import services.Service
import services.organisation.OrganisationBudgetService

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/01/10.
  */
class OrganisationBudgetServiceImpl extends OrganisationBudgetService with Service{

  override def save(budget: OrganisationBudget): Future[ResultSet] = {
    OrganisationBudgetRepository.save(budget)
  }

  override def getOrganisationBudgets(orgCode: String): Future[Seq[OrganisationBudget]] = {
    OrganisationBudgetRepository.getOrganisationBudgets(orgCode)
  }

  override def getOrganisationBudgetByYear(orgCode: String, year: Int): Future[Seq[OrganisationBudget]] = {
    OrganisationBudgetRepository.getOrganisationBudgetByYear(orgCode,year)
  }

  override def getOrganisationBudgetItemByYear(orgCode: String, year: Int, itemCode: String): Future[Option[OrganisationBudget]] = {
    OrganisationBudgetRepository.getOrganisationBudgetItemByYear(orgCode,year,itemCode)
  }
}
