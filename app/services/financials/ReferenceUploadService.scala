package services.financials

import com.websudos.phantom.dsl._
import com.websudos.phantom.reactivestreams._
import domain.financials.ReferenceUploads
import services.financials.Impl.ReferenceUploadServiceImpl

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/09/24.
  */
trait ReferenceUploadService {
  def cretaeOrUpdate(referenceuploads:ReferenceUploads):Future[ResultSet]
  def findByorgCode(orgCode:String):Future[Seq[ReferenceUploads]]
  def findBysessionId(orgCode:String,sessionId:String):Future[Seq[ReferenceUploads]]
  def findById(orgCode:String,sessionId:String,referenceId:String):Future[Option[ReferenceUploads]]
}

object ReferenceUploadService{
  def apply: ReferenceUploadService = new ReferenceUploadServiceImpl()
}