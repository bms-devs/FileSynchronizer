package org.danielwojda.alfrescofileloader.fileuploader

import org.apache.http.client.fluent.Request
import org.apache.http.client.utils.URIBuilder


object Authentication {
  val serviceUrl = "/alfresco/service/api/login"
  private val startTag = "<ticket>"
  private val endTag = "</ticket>"


  def getToken(serverUrl: String, login: String, password: String): String = {

    val url = new URIBuilder(serverUrl + serviceUrl)
      .addParameter("u", login)
      .addParameter("pw", password)
      .build()

    val response = Request.Get(url)
      .connectTimeout(1000)
      .socketTimeout(1000)
      .execute().returnContent().asString()
    
    val ticketStartIndex = response.indexOf(startTag) + startTag.length
    val ticketEndIndex = response.indexOf(endTag)
    
    response.substring(ticketStartIndex, ticketEndIndex)
  }
}