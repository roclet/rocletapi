package domain.organisation

import org.joda.time.DateTime
import play.api.libs.json.Json
/**
  * Created by hashcode on 2016/08/12.
  */
case class OrganisationDocuments(orgCode:String,
                                url:String,
                                docType:String,
                                date:DateTime,
                                extension:String
                                )
object OrganisationDocuments{
  implicit val orgdocFmt=Json.format[OrganisationDocuments]
}
