package services.financials.Impl

import java.io.File

import com.datastax.driver.core.ResultSet
import conf.util.MarginKeys
import domain.financials.Budget
import domain.organisation.UploadMetaData
import okhttp3._
import repositories.financials.BudgetRepository
import services.Service
import services.financials.BudgetService
import services.organisation.OrganisationServices

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by kuminga on 2016/11/28.
  */
class BudgetServiceImpl extends BudgetService with Service {
  override def save(budget: Budget): Future[ResultSet] = {
    BudgetRepository.save(budget)
  }

  override def getOrganisationBudgets(orgCode: String): Future[Seq[Budget]] = {
    BudgetRepository.getOrganisationBudgets(orgCode)
  }

  override def getOrganisationBudgetByYear(orgCode: String, year: Int): Future[Seq[Budget]] = {

    val results = OrganisationServices.apply().getOrganisationByCode(orgCode) map (organisation => {

      if (organisation.get.details.getOrElse(MarginKeys.FINACIAL_YEAR_MONTH, "").equalsIgnoreCase(MarginKeys.March)) {
        for {
          getBudgetPreviousYear <- BudgetRepository.getOrganisationBudgetByYear(orgCode, year - 1)
          getBudgetForCurrentYear <- BudgetRepository.getOrganisationBudgetByYear(orgCode, year)
        } yield getBudgetPreviousYear.filter(budget => budget.month > 2) ++ getBudgetForCurrentYear.filter(budget => budget.month < 3)

      } else {
        BudgetRepository.getOrganisationBudgetByYear(orgCode, year)
      }
    })
    for {
      flattenResult <- results
      finalBudgetList <- flattenResult
    } yield finalBudgetList
  }

  override def getOrganisationBudgetItemByMonth(orgCode: String, year: Int, month: Int, item: String): Future[Option[Budget]] = {
    BudgetRepository.getOrganisationBudgetItemByMonth(orgCode, year, month, item)
  }

  override def getItemsBudgetByMonth(orgCode: String, year: Int, month: Int): Future[Seq[Budget]] = {
    BudgetRepository.getItemsBudgetByMonth(orgCode, year, month)
  }

  override def processFile(file: File, data: UploadMetaData): Future[String] = {
    val meta = UploadMetaData(data.email, data.date, "orgCode", data.orgCode, data.url + "/upload/budget", data.fileId)

    val MEDIA_TYPE_CSV = MediaType.parse("text/csv")
    val requestBody = new MultipartBody.Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart("upload", file.getName, RequestBody.create(MEDIA_TYPE_CSV, file))
      .addFormDataPart("orgCode", meta.orgCode)
      .build()
    val request = new Request.Builder()
      .header("Authorization", "Bearer " + meta.token)
      .url(meta.url)
      .post(requestBody)
      .build()

    val content = new OkHttpClient().newCall(request).execute().body().string()
    Future {
      content
    }
  }
}
