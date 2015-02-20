package org.danielwojda.alfrescofileloader.config

import spray.json.JsonParser


case class Connection(login: String, password: String, alfrescoUrl: String)


case class FileToLoad(source: String, destination: String) {
  def fileName(): String = source.split('/').last
}


case class DirectoryToLoad(source: String, destination: String) {
  
  def filesToLoad(): List[FileToLoad] = {
    new java.io.File(source)
      .listFiles
      .filter(file => !file.isDirectory)
      .map(file => new FileToLoad(file.getPath, destination))
      .toList
  }
  
}


case class Configuration(connection: Connection, files: List[FileToLoad], directories: List[DirectoryToLoad]){

  def allFilesToLoad(): List[FileToLoad] = {
    val filesFromDirectories = directories.flatMap(directory => directory.filesToLoad())
    files ::: filesFromDirectories
  }

}


object Configuration{
  import MyJsonProtocol._

  def load(configFileName: String = "config.json"): Configuration = {
    val configFile = loadConfigFile(configFileName)
    val config = JsonParser(configFile).convertTo[Configuration]
    println("config: " + config.toString)

    config
  }

  private def loadConfigFile(configFileName: String): String = {
    scala.io.Source.fromFile(configFileName).getLines().mkString
  }
  
}
