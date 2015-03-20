package pl.com.bms.fileSynchronizer.config

import spray.json.DefaultJsonProtocol

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit val connectionFormat3 = jsonFormat3(Connection)
//  implicit val directoryToLoadFormat = jsonFormat1(DirectoryToLoad.apply)
  implicit val configurationFormat = jsonFormat5(Configuration.apply)
}
