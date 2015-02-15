package org.danielwojda.alfrescofileloader.fileuploader

import org.danielwojda.alfrescofileloader.config.{Connection, FileToLoad}

import scalaj.http.{Http, HttpOptions, HttpResponse, MultiPart}


class FileUploader(val conn: Connection) {
  val url = conn.alfrescoUrl + "/alfresco/service/api/upload"
  val token = Authentication.getToken(conn.alfrescoUrl, conn.login, conn.password)

  
  def uploadFile(fileToLoad: FileToLoad): HttpResponse[String] = {
    val fileBytes = loadFileFromDisk(fileToLoad.source)
    uploadFile(fileToLoad, fileBytes)
  }


  def uploadFile(file: FileToLoad, fileBytes: Array[Byte]): HttpResponse[String] = {
    Http(url)
      .postMulti(MultiPart("script", file.fileName(), "application/javascript", fileBytes))
      .postForm(Seq(
        "uploaddirectory" -> file.destination,
        "overwrite" -> "yes"))
      .param("alf_ticket", token)
      .option(HttpOptions.connTimeout(10000))
      .asString
  }
  

  private def loadFileFromDisk(filePath: String): Array[Byte] = {
    import java.nio.file.{Files, Paths}
    Files.readAllBytes(Paths.get(filePath))
  }
}
