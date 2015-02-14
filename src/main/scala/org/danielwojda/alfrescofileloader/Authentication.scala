package org.danielwojda.alfrescofileloader

import scalaj.http.{HttpResponse, Http}


object Authentication {
  val serviceUrl = "/alfresco/service/api/login"

  def getToken(serverUrl: String, login: String, password: String): String = {
    val response: HttpResponse[String] = Http(serverUrl + serviceUrl)
      .params("u" -> login, "pw" -> password)
      .asString
    response.body
  }
}