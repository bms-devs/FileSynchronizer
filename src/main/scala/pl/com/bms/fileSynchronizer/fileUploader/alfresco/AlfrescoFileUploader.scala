package pl.com.bms.fileSynchronizer.fileUploader.alfresco

import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.impl.client.HttpClients
import pl.com.bms.fileSynchronizer.config.Configuration
import pl.com.bms.fileSynchronizer.fileUploader.{FileUploader, UploadResult}


class AlfrescoFileUploader(val configuration: Configuration) extends FileUploader {
  val baseUrl = configuration.connection.url + "/alfresco/service/api/upload"
  val token = Authentication.getToken(configuration.connection.url, configuration.connection.login, configuration.connection.password)

  override def uploadFile(path: String): UploadResult = {
    val sourceFile = configuration.sourceRoot + path
    val uploadFilePart = new FileBody(new java.io.File(sourceFile))

    val reqEntity = MultipartEntityBuilder.create()
      .addPart("filedata", uploadFilePart)
      .addTextBody("destination", configuration.destinationRoot)
      .addTextBody("path", path)
      .build()

    val url = new URIBuilder(baseUrl)
      .addParameter("alf_ticket", token)
      .build()

    val httpPost = new HttpPost(url)
    httpPost.setEntity(reqEntity)

    val response = HttpClients.createDefault().execute(httpPost)
    UploadResult(response.getStatusLine.getStatusCode.toString, Some(response.getStatusLine.getReasonPhrase))
  }

}
