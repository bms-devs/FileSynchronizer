package pl.com.bms.fileSynchronizer.config

import spray.json.JsonParser

case class Connection(login: String, password: String, url: String)

case class Configuration(
                          connection: Connection,
                          sourceRoot: String,
                          destinationRoot: String,
                          files: List[String],
                          directories: List[String]) extends Product{

  val hasAlfrescoDestination = destinationRoot.startsWith("workspace://")

  def allFilesToLoad = {
    val filesFromDirectories = directories.flatMap(directory => DirectoryToLoad(sourceRoot, directory).filesToLoad())
    files ::: directories ::: filesFromDirectories
  }
  
}

object Configuration{

  def load(configFileName: String = "config.json"): Configuration = {
    import pl.com.bms.fileSynchronizer.config.MyJsonProtocol._
    val configFile = loadConfigFile(configFileName)
    val config = JsonParser(configFile).convertTo[Configuration]
    config
  }

  private def loadConfigFile(configFileName: String): String = {
    scala.io.Source.fromFile(configFileName).getLines().mkString
  }
  
}
