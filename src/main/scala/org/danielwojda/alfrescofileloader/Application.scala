package org.danielwojda.alfrescofileloader

import java.nio.file.Paths

import org.danielwojda.alfrescofileloader.config.Configuration
import org.danielwojda.alfrescofileloader.fileuploader.FileUploader
import org.danielwojda.alfrescofileloader.watch.FileWatcher


class Application(config: Configuration) {
  val files = config.files
  val uploader = new FileUploader(config.connection)


  def upload() = {
    files.foreach(fileToLoad => {
      val response = uploader.uploadFile(fileToLoad)
      println("File: " + fileToLoad.source + " status:" + response)
    })
    this
  }


  def startWatching(): Unit = {
    val watcher = new FileWatcher()
    val handlerFactory = new HandlerFactory(uploader)

    files.foreach(fileToLoad => {
      val path = Paths.get(fileToLoad.source)
      val handler = handlerFactory.getHandlerFor(fileToLoad)
      watcher.addToWatched(path, handler)
    })

    watcher.startWatching() //infinite loop
  }
}

