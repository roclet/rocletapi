package services.financials.Impl

import java.io.File

import domain.organisation.UploadMetaData
import domain.util.FileResults
import okhttp3._
import org.asynchttpclient.AsyncHttpClient
import org.asynchttpclient.Dsl._
import org.asynchttpclient.request.body.multipart.{FilePart, StringPart}
import play.api.libs.json.Json
import services.financials.FileUploadPostService
import services.util.ApiKeysService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * Created by hashcode on 2016/10/14.
  */
class FileUploadPostServiceImpl extends FileUploadPostService {
  val keysService = ApiKeysService.apply
  val conf = config()
    .setConnectTimeout(100) //
    .setMaxConnections(200) //
    .setMaxConnectionsPerHost(200)
    .setPooledConnectionIdleTimeout(100)
    .setConnectionTtl(500)
    .setRequestTimeout(5 * 60 * 1000) // 5 minutes
    .build()


  override def processFile(file: File, uploadMetaData: UploadMetaData): Future[String] = {
    val client: AsyncHttpClient = asyncHttpClient(conf)
    val postBuilder = client.preparePost(uploadMetaData.url + "/uploaddoc")
    val builder = postBuilder
      .addHeader("Authorization", "Basic " + uploadMetaData.token)
      .addBodyPart(new StringPart("token", uploadMetaData.token, "UTF-8"))
      .addBodyPart(new StringPart("date", uploadMetaData.date.toString, "UTF-8"))
      .addBodyPart(new StringPart("orgCode", uploadMetaData.orgCode, "UTF-8"))
      .addBodyPart(new StringPart("email", uploadMetaData.email, "UTF-8"))
      .addBodyPart(new StringPart("fileId", uploadMetaData.fileId, "UTF-8"))
      .addBodyPart(new FilePart("upload", file))
    val response = client
      .executeRequest(builder.build())
      .toCompletableFuture
      .get()
      .getResponseBody
    client.close()
    Future {
      response
    }
  }

  override def storeFile(file: File, uploadMetaData: UploadMetaData): Future[FileResults] = {
    val MEDIA_TYPE_CSV = MediaType.parse("text/csv")
    val requestBody = new MultipartBody.Builder()
      .setType(MultipartBody.FORM)
      .addFormDataPart("upload", file.getName, RequestBody.create(MEDIA_TYPE_CSV, file))
      .build()
    val request = new Request.Builder()
      .header("Authorization", "Bearer " + uploadMetaData.token)
      .url(uploadMetaData.url + "/api/upload")
      .post(requestBody)
      .build()
    Future {
      val content = new OkHttpClient().newCall(request).execute().body().string()
      Json.parse(content).as[List[FileResults]].head
    }
  }

  override def processApproval(url:String, orgcode:String, year:Int,month:Int): Future[String] = {
    val  request = new Request.Builder().url(url + "/approved/upload?orgcode="+orgcode+"&year="+year+"&month="+month).build()
    val response = new OkHttpClient().newCall(request).execute()
    Future{
      response.body().string()
    }
  }

  override def processRejection(url:String, orgcode:String, year:Int,month:Int): Future[String] = {
    val  request = new Request.Builder().url(url + "/rejected/upload?orgcode="+orgcode+"&year="+year+"&month="+month).build()
    val response = new OkHttpClient().newCall(request).execute()
    Future{
      response.body().string()
    }
  }
}
