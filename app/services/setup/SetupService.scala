package services.setup

import conf.security.AuthUtil
import conf.util.MarginKeys
import domain.organisation.Organisation
import domain.people.{User, UserRole}
import repositories.address.{AddressTypeRepository, GlobalLocationRepository, LocationTypeRepository}
import repositories.contacts._
import repositories.financials._
import repositories.hr.{EmployeeRepository, NationalityRepository, PositionRepository}
import repositories.messages.MessagesRepository
import repositories.organisation._
import repositories.people._
import repositories.portfolio._
import repositories.reports.{FinancialRatioRepository, WeightingRepository}
import repositories.subscriptions.SubscriptionsRepository
import repositories.syslogs.SystemLogEventsRepository
import repositories.util._
import services.Service

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by kuminga on 2016/08/18.
  */
object SetupService extends Service {
  val details = Map("contactEmail" -> "admin@test.com")
  val organisation = Organisation("MARGINM", "Margin Mentor", "COMMERCIAL", MarginKeys.ACTIVE, "admin@test.com", details)

  val testUser = User("admin@test.com",
    "System",
    "Admininstrator",
    "Admin",
    AuthUtil.encode("admin"),
    MarginKeys.ACTIVE,
    MarginKeys.MAILORG)

  val hrAdmin = User("hrmanager@test.com",
    "Human Resource",
    "Manager",
    "HRManager",
    AuthUtil.encode("admin"),
    MarginKeys.ACTIVE,
    MarginKeys.MAILORG)

  val hrManagerRole = UserRole(MarginKeys.MAILORG, "hrmanager@test.com", "MANAGER")

  val userRole = UserRole(MarginKeys.MAILORG, "admin@test.com", "ADMIN")

  def runSetUp = for {

  /**
    * Create Tables financials.
    */
    createTable <- CredentialRepository.create.ifNotExists().future()
    createTable <- TitleRepository.create.ifNotExists().future()
    createTable <- UserSessionsRepository.create.ifNotExists().future()
    createTable <- IdentityTypesRepository.create.ifNotExists().future()
    createTable <- StatusRepository.create.ifNotExists().future()
    createTable <- RaceRepository.create.ifNotExists().future()
    createTable <- OrganisationStatusRepository.create.ifNotExists().future()

    createTable <- FinanceStatementCategoryCodeMappingRepository.create.ifNotExists().future()
    createTable <- CustomerUploadsRepository.create.ifNotExists().future()

    createTable <- MappingTypeRepository.create.ifNotExists().future()
    createTable <- OrganisationTargetRepository.create.ifNotExists().future()
    createTable <- ReferenceUploadsRepository.create.ifNotExists().future()
    createTable <- SettingUploadsRepository.create.ifNotExists().future()
    createTable <- GlobalLocationRepository.create.ifNotExists().future()

    /**
      * Create Tables financials.
      */
    createTable <- OrganisationRepository.create.ifNotExists().future()
    createTable <- ApiKeysRepository.create.ifNotExists().future()
    createTable <- KeysRepository.create.ifNotExists().future()
    createTable <- MailRepository.create.ifNotExists().future()
    createTable <- TokenRepository.create.ifNotExists().future()
    createTable <- UserRepository.create.ifNotExists().future()

    createTable <- AccountSystemsRepository.create.ifNotExists().future()
    createTable <- CodeMappingSystemRepository.create.ifNotExists().future()

    /**
      * Create Tables contacts.
      */
    createTable <- ContactTypeRepository.create.ifNotExists().future()


    /**
      * Create Tables messages.
      */
    createTable <- MessagesRepository.create.ifNotExists().future()
    createTable <- FinancialApprovedStatementRepository.create.ifNotExists().future()

    /**
      * Create Tables organisation.
      */
    createTable <- OrganisationDocRepository.create.ifNotExists().future()
    createTable <- OrganisationRepository.create.ifNotExists().future()
    createTable <- OrganisationTypeRepository.create.ifNotExists().future()
    createTable <- OrganisationSubscrRepository.create.ifNotExists().future()
    createTable <- OrganisationLocationRepository.create.ifNotExists().future()
    createTable <- OrganisationAddressRepository.create.ifNotExists().future()
    createTable <- OrganisationContactRepository.create.ifNotExists().future()
    createTable <- SubsidizeRepository.create.ifNotExists().future()
    createTable <- SubsidizeHistoryRepository.create.ifNotExists().future()

    /**
      * Create Tables address.
      */
    createTable <- AddressTypeRepository.create.ifNotExists().future()
    createTablle <- LocationTypeRepository.create.ifNotExists().future()
    createTable <- UserAddressRepository.create.ifNotExists().future()
    createTable <- OrganisationBudgetRepository.create.ifNotExists().future()

    /**
      * Create Tables people.
      */
    createTable <- UserContactRepository.create.ifNotExists().future()
    createTable <- UserDemographicsRepository.create.ifNotExists().future()
    createTable <- UserIdentitiesRepository.create.ifNotExists().future()
    createTable <- UserLogActivitiesRepository.create.ifNotExists().future()
    createTable <- UserOptEventRepository.create.ifNotExists().future()
    createTable <- UserOtpRepository.create.ifNotExists().future()
    createTable <- UserRepository.create.ifNotExists().future()
    createTable <- UserRoleRepository.create.ifNotExists().future()
    createTable <- UserSessionEventRepository.create.ifNotExists().future()
    createTable <- UserSessionsRepository.create.ifNotExists().future()
    createTable <- UserStatusChangeRepository.create.ifNotExists().future()


    /**
      * Create Tables subscriptions.
      */
    createTable <- SubscriptionsRepository.create.ifNotExists().future()

    // create System Log Event table
    createTable <- SystemLogEventsRepository.create.ifNotExists().future()

    // Create Porfolio tables
    createTable <- AdvisorPortfolioStatusRepository.create.ifNotExists().future()
    createTable <- AdvisorPortfolioRepository.create.ifNotExists().future()
    createTable <- FundManagerRepository.create.ifNotExists().future()
    createTable <- FundPortfolioRepository.create.ifNotExists().future()
    createTable <- FundPortfolioStatusRepository.create.ifNotExists().future()
    createTable <- FundManagerStatusRepository.create.ifNotExists().future()

    /**
      * Create Tables util.
      */
    createTable <- ApiKeysRepository.create.ifNotExists().future()
    //    createTable <- CredentialRepository.create.ifNotExists().future()
    createTable <- GenderRepository.create.ifNotExists().future()
    createTable <- KeysRepository.create.ifNotExists().future()
    createTable <- MailRepository.create.ifNotExists().future()
    createTable <- RoleRepository.create.ifNotExists().future()
    createTable <- TokenRepository.create.ifNotExists().future()
    createTable <- TitleRepository.create.ifNotExists().future()
    createTable <- UserEmailRepository.create.ifNotExists().future()

    createTable <- BudgetRepository.create.ifNotExists().future()
    createTable <- CustomerRejectedUploadsRepository.create.ifNotExists().future()

    // Create
    org <- OrganisationRepository.save(organisation)
    user <- UserRepository.save(testUser)
    role <- UserRoleRepository.save(userRole)
    user <- UserRepository.save(hrAdmin)
    role <- UserRoleRepository.save(hrManagerRole)
    ext <- ExternalRolesRepository.create.ifNotExists().future()

    // Uploads
    uploads <- OrganisationFinancialUploadsEventsRepository.create.ifNotExists().future()
    uploads <- OrganisationFinancialUploadsRepository.create.ifNotExists().future()

    //Fin Tables
    fin <- FinancialStatementRecordsRepository.create.ifNotExists().future()
    hist <- CustomerUploadsHistoryRepository.create.ifNotExists().future()

    // HR TAbles
    emp <-EmployeeRepository.create.ifNotExists().future()
    pos <-PositionRepository.create.ifNotExists().future()
    nati <-NationalityRepository.create.ifNotExists().future()

    // KPAs
    kpa <- WeightingRepository.create.ifNotExists().future()
    kpi <-FinancialRatioRepository.create.ifNotExists().future()

  } yield createTable
}
