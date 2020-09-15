package controllers.financials

import javax.inject.Inject

import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter
import play.api.routing.sird._

/**
  * Created by kuminga on 2016/09/27.
  */
class FinancialsRouter @Inject()
(customerupload: CustomerUploadsController)
(accountsystems: AccountSystemsController)
(codemappingsystem: CodeMappingSystemController)
(financialStatementRecordsController: FinancialStatementRecordsController)
(organisationFileUploadController: OrganisationFileUploadController)
(budgetcontroller: BudgetController)
(financialApprovedStatementController: FinancialApprovedStatementController)
(customerRejectedUploadsController: CustomerRejectedUploadsController)
(organisationBudgetController: OrganisationBudgetController)
(incomeStatementController: IncomeStatementController)
(settingUploadsController: SettingUploadsController) extends SimpleRouter {
  override def routes: Routes = {

    case POST(p"/settings/upload") =>
      settingUploadsController.saveOrUpdate
    case GET(p"/settings/accountsystem/$id") =>
      settingUploadsController.getAccountingSystemById(id)
    case GET(p"/settings/update/accounting/$accountingstemid") =>
      settingUploadsController.getUpdateWithAccountSystem(accountingstemid)
    case GET(p"/settings/update/codemapping/$accountingstemid") =>
      settingUploadsController.getUpdateWithCodeMapping(accountingstemid)
    case GET(p"/settings/organisation/upload/$org") =>
      settingUploadsController.getOrganisationSettingUpload(org)
// Customer Uploads
    case GET(p"/customerupload/$orgCode") =>
      customerupload.getUploadByOrganisation(orgCode)
    case GET(p"/customerupload/$orgCode/${int(year)}") =>
      customerupload.getUploadsYear(orgCode, year)
    case GET(p"/customerupload/$orgCode/${int(year)}/${int(month)}") =>
      customerupload.getUploadsByMonth(orgCode, year, month)
    case GET(p"/customerupload/record/$orgCode/$fileId") =>
      customerupload.getCustomerUploadsByFileId(orgCode, fileId)
    case GET(p"/customerupload/exist/$orgCode/${int(year)}/${int(month)}") =>
      customerupload.doesUploadExist(orgCode, year, month)

// Customer Rejected Uploads
    case GET(p"/rejected/customerupload/$orgCode") =>
      customerRejectedUploadsController.getUploadByOrganisation(orgCode)
    case GET(p"/rejected/customerupload/$orgCode/${int(year)}") =>
      customerRejectedUploadsController.getUploadsYear(orgCode, year)
    case GET(p"/rejected/customerupload/$orgCode/${int(year)}/${int(month)}") =>
      customerRejectedUploadsController.getUploadsByMonth(orgCode, year, month)
    case GET(p"/rejected/customerupload/record/$orgCode/$fileId") =>
      customerRejectedUploadsController.getCustomerUploadsByFileId(orgCode, fileId)

    case POST(p"/accountsystem") =>
      accountsystems.saveOrupdate
    case GET(p"/accountsystem/id/$accountingSystem") =>
      accountsystems.getAccountingSystemById(accountingSystem)
    case GET(p"/accountsystem/all") =>
      accountsystems.getAllAccountingSystems

    case POST(p"/code/mapping/create") =>
      codemappingsystem.saveOrupdate
    case GET(p"/code/mapping/get/$accountsystem/$id") =>
      codemappingsystem.getCodeMappingSystemById(accountsystem,id)
    case GET(p"/code/mapping/all/$accountingSystemId") =>
      codemappingsystem.getAllCodemappingSystems(accountingSystemId)
    case GET(p"/code/mapping/get/all") =>
      codemappingsystem.getAllCodeMappings

    case POST(p"/code/category/mapping/create") =>
      codemappingsystem.saveOrupdateFinanceStatementCategoryCodeMapping
    case GET(p"/code/category/mapping/$accountsystem/$id") =>
      codemappingsystem.getCategoriesByFinType(accountsystem,id)
    case GET(p"/code/category/mapping/all/$orgCode") =>
      codemappingsystem.getOrgCategories(orgCode)

    case GET(p"/code/category/mapping/$orgCode/$financetype/$category") =>
      codemappingsystem.getSubCategories(orgCode,financetype,category)


    case POST(p"/code/mapping/types/create") =>
      codemappingsystem.saveOrupdateMappingType
    case GET(p"/code/mapping/types/get/$id") =>
      codemappingsystem.getMappingTypeById(id)
    case GET(p"/code/mapping/types/all") =>
      codemappingsystem.findAll

      // Statements

    case GET(p"/statement/getByCategory/$org/$stmt/$category") =>
      financialStatementRecordsController.getOrganisationStatementsByCategory(org, stmt, category)
    case GET(p"/statement/getall/$org") =>
      financialStatementRecordsController.getOrganisationStatements(org)
    case GET(p"/statement/finance/$org/$stmt") =>
      financialStatementRecordsController.getOrganisationStatementsByCategory(org,stmt)

    // Approved Statements
    case GET(p"/approved/statement/getByCategory/$org/$stmt/$category") =>
      financialApprovedStatementController.getOrganisationStatementsByCategory(org, stmt, category)
    case GET(p"/approved/statement/getall/$org") =>
      financialApprovedStatementController.getOrganisationStatements(org)
    case GET(p"/approved/statement/finance/$org/$stmt") =>
      financialApprovedStatementController.getOrganisationStatementsByCategory(org,stmt)


    case POST(p"/upload") =>
      organisationFileUploadController.streamUpload
    case GET(p"/file/uploaded/$orgCode/$fileId") =>
      organisationFileUploadController.getUploadedFile(orgCode, fileId)
    case GET(p"/file/status/$fileId") =>
      organisationFileUploadController.getUploadedFileStatus(fileId)
    case GET(p"/file/status/history/$fileId") =>
      organisationFileUploadController.getUploadedFileStatusHistory(fileId)
    case GET(p"/file/uploads/$orgCode") =>
      organisationFileUploadController.getOrganisationUploadedFiles(orgCode)
    case POST(p"/file/uploads/events/create/$orgCode") =>
      organisationFileUploadController.saveFileEvents(orgCode)
    case GET(p"/file/status/all") =>
      organisationFileUploadController.getAllUploadedFileStatus()

      // Budget
    case POST(p"/budget/create") =>
      budgetcontroller.saveOrupdate
    case GET(p"/budget/all/$orgCode") =>
      budgetcontroller.getOrganisationBudgets(orgCode)
    case GET(p"/budget/year/$orgCode/${int(year)}") =>
      budgetcontroller.getOrganisationBudgetByYear(orgCode,year)
    case GET(p"/budget/year/setitems/$orgCode/${int(year)}") =>
      budgetcontroller.getBudgetSetItems(orgCode,year)
    case GET(p"/budget/year/notsetitems/$orgCode/${int(year)}") =>
      budgetcontroller.getBudgetSetNotItems(orgCode,year)
    case GET(p"/budget/month/setitems/$orgCode/${int(year)}/${int(month)}") =>
      budgetcontroller.getSetItemsBudgetByMonth(orgCode,year,month)
    case GET(p"/budget/month/notsetitems/$orgCode/${int(year)}/${int(month)}") =>
      budgetcontroller.getItemsNotBudgetByMonth(orgCode,year,month)

    // Organisation Budget
    case POST(p"/orgbudget/create") =>
      organisationBudgetController.saveOrupdate
    case GET(p"/orgbudget/all/$orgCode") =>
      organisationBudgetController.getOrganisationBudgets(orgCode)
    case GET(p"/orgbudget/year/$orgCode/${int(year)}") =>
      organisationBudgetController.getOrganisationBudgetByYear(orgCode,year)
    case GET(p"/orgbudget/year/values/$orgCode/${int(year)}") =>
      organisationBudgetController.getBudgetSetItemWithValues(orgCode,year)
    case GET(p"/orgbudget/year/item/$orgCode/${int(year)}/" ? q"query=$item") =>
      organisationBudgetController.getBudgetItem(orgCode,year,item)
    case POST(p"/orgbudget/upload") =>
      organisationBudgetController.streamUpload


    // New API

    case GET(p"/is/month/$orgCode/${int(year)}") =>
      incomeStatementController.getIncomeStatementByMonth(orgCode,year)
    case GET(p"/is/year/$orgCode/${int(start)}/${int(end)}") =>
      incomeStatementController.getIncomeStatementByYear(orgCode,start,end)

  }
}
