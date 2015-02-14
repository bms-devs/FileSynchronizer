package org.danielwojda.alfrescofileloader.config

import spray.json.DefaultJsonProtocol

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val connectionFormat = jsonFormat3(Connection)
  implicit val fileToLoadFormat = jsonFormat2(FileToLoad)
  implicit val directoryToLoadFormat = jsonFormat2(DirectoryToLoad)
  implicit val configurationFormat = jsonFormat3(Configuration.apply)
}
