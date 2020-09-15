package controllers.organisation

import java.io._
import java.util.Date

import domain.organisation.UploadMetaData
import domain.util.FileResults
import okhttp3._
import org.scalatestplus.play._
import play.api.libs.json.Json
import services.financials.FileUploadPostService

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


/**
  * Created by hashcode on 2016/10/13.
  */
class OrganisationFileUploadTest extends PlaySpec with OneServerPerSuite{

  val url = getClass().getResource("budget.csv")
  println(" The USER IS ", url)
  val file = new File(url.getPath())
  val logo = new FileInputStream(file)

  val BASE_URL = "http://192.168.0.110:19004/upload/budget"

  "OrganisationFileUploadController" must {
    "upload a file successfully" in {

      val meta = UploadMetaData("test@tes.com", new Date(),"orgCode","MARGINM",BASE_URL,"Chanda Phiri")


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
//        Json.parse(content).as[List[FileResults]].head

//      val service = Await.result(FileUploadPostService.apply.processFile(file,meta), 2.minutes)
      println(" The Response >>>>", content)

    }
  }


//  def postSource(tmpFile: File): Source[MultipartFormData.Part[Source[ByteString, _]], _] = {
//    import play.api.mvc.MultipartFormData._
//
//    val dp = FilePart("file", "hello.txt", Option("text/plain"),
//      FileIO.fromPath(tmpFile.toPath)) :: DataPart("key", "value") :: List()
//    dp
//    Source(dp)
//  }


}
