package org.danielwojda.alfrescofileloader.fileuploader

import java.io.File
import java.nio.file.Paths

import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.impl.client.HttpClients
import org.danielwojda.alfrescofileloader.config.{Connection, FileToLoad}



class AlfrescoFileUploader(val conn: Connection) extends FileUploader {
  val baseUrl = conn.alfrescoUrl + "/alfresco/service/api/upload"
  val token = Authentication.getToken(conn.alfrescoUrl, conn.login, conn.password)


  override def uploadFile(fileToLoad: FileToLoad):Int = {
    val file = Paths.get(fileToLoad.source).toFile
    uploadFile(fileToLoad.destination, file)
  }


  private def uploadFile(destination: String, f: File): Int = {

    val uploadFilePart = new FileBody(f)

    val reqEntity = MultipartEntityBuilder.create()
      .addPart("filedata", uploadFilePart)
      .addTextBody("destination", destination)
      .build()

    val url = new URIBuilder(baseUrl)
      .addParameter("alf_ticket", token)
      .build()

    val httpPost = new HttpPost(url)
    httpPost.setEntity(reqEntity)

    val response = HttpClients.createDefault().execute(httpPost)
    response.getStatusLine.getStatusCode
  }


  private def loadFileFromDisk(filePath: String): Array[Byte] = {
    import java.nio.file.{Files, Paths}
    Files.readAllBytes(Paths.get(filePath))
  }
}
