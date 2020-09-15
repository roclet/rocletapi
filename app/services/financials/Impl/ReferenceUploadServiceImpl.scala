package services.financials.Impl


import com.websudos.phantom.dsl._
import domain.financials.ReferenceUploads
import repositories.financials.ReferenceUploadsRepository
import services.Service
import services.financials.ReferenceUploadService

import scala.concurrent.Future
/**
  * Created by kuminga on 2016/09/24.
  */
class ReferenceUploadServiceImpl extends ReferenceUploadService with Service{

  override def cretaeOrUpdate(referenceuploads:ReferenceUploads):Future[ResultSet]={

    ReferenceUploadsRepository.save(referenceuploads)
  }
  override def findByorgCode(orgCode:String):Future[Seq[ReferenceUploads]]={
    ReferenceUploadsRepository.findByorgCode(orgCode)
  }
  override def findBysessionId(orgCode:String,sessionId:String):Future[Seq[ReferenceUploads]]={

    ReferenceUploadsRepository.findBysessionId(orgCode,sessionId)
  }
  override def findById(orgCode:String,sessionId:String,referenceId:String):Future[Option[ReferenceUploads]]={

    ReferenceUploadsRepository.findById(orgCode,sessionId,referenceId)
  }

}
