package org.danielwojda.alfrescofileloader

import org.danielwojda.alfrescofileloader.config.{FileToLoad, Connection, Configuration}
import scalaj.http.{HttpOptions, MultiPart, HttpResponse, Http}


object Main {

  def main(args: Array[String]) {
    val config = Configuration.load()

    val uploader = new FileUploader(config.connection)
    
    config.files.foreach(fileToLoad => {
      val fileBytes = loadFileFromDisk(fileToLoad.source)
      uploader.uploadFile(fileToLoad, fileBytes)
    })

  }

  
  def loadFileFromDisk(filePath: String): Array[Byte] = {
    import java.nio.file.{Files, Paths}

    Files.readAllBytes(Paths.get(filePath))
  }

}
