package org.danielwojda.alfrescofileloader

import org.danielwojda.alfrescofileloader.config.{FileToLoad, Connection}
import scalaj.http.{Http, HttpOptions, MultiPart, HttpResponse}


class FileUploader(val conn: Connection) {
  val url = conn.alfrescoUrl + "/alfresco/service/api/upload"
  val token = Authentication.getToken(conn.alfrescoUrl, conn.login, conn.password)

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

}
