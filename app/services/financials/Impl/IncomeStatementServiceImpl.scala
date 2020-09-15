package services.financials.Impl


import okhttp3.{OkHttpClient, Request}
import services.Service
import services.financials.IncomeStatementService
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/02/02.
  */
class IncomeStatementServiceImpl extends IncomeStatementService with Service{
  override def getIncomeStatementByMonth(orgCode: String, year: Int,url:String): Future[String] = {
    val client = new OkHttpClient()
    val request = new Request.Builder()
      .url(url+"/fstatement/monthly/"+orgCode+"/"+year)
      .build()
    val response = client.newCall(request).execute()

    println(" Container Value ", response.body().string())

    Future{
      response.body().string()
    }

  }



  override def getIncomeStatementByYear(orgCode: String, start: Int, end: Int, url:String): Future[String] = {

    Future{
      "Not Implemented "
    }
  }
}
