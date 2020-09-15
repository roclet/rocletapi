package services.financials

import services.financials.Impl.IncomeStatementServiceImpl

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/02/02.
  */
trait IncomeStatementService {
  def getIncomeStatementByMonth(orgCode:String,year:Int, url:String): Future[String]
  def getIncomeStatementByYear(orgCode:String,start:Int, end:Int, url:String): Future[String]
//  def getIncomeStatementVertical(orgCode:String,year:Int): Future[String]
//  def getIncomeStatementHorizontal(orgCode:String,year:Int): Future[String]
//  def getIncomeStatementByMonth(orgCode:String,year:Int): Future[String]
//  def getIncomeStatementByMonth(orgCode:String,year:Int): Future[String]
//  def getIncomeStatementByMonth(orgCode:String,year:Int): Future[String]



}

object IncomeStatementService {
  def apply: IncomeStatementService = new IncomeStatementServiceImpl()
}
