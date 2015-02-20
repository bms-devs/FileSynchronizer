package org.danielwojda.alfrescofileloader

import java.nio.file.Paths

import org.danielwojda.alfrescofileloader.config.Configuration
import org.danielwojda.alfrescofileloader.fileuploader.{TimePrinter, AlfrescoFileUploader}
import org.danielwojda.alfrescofileloader.watch.FileWatcher


class Application(config: Configuration) {

  val files = config.allFilesToLoad()
  val uploaderWithTimePrinter = new TimePrinter(new AlfrescoFileUploader(config.connection))


  def upload() = {
    files.foreach(fileToLoad => {
      uploaderWithTimePrinter.uploadFile(fileToLoad)
    })
    this
  }


  def startWatching(): Unit = {
    val watcher = new FileWatcher()
    val handlerFactory = new HandlerFactory(uploaderWithTimePrinter)

    files.foreach(fileToLoad => {
      val path = Paths.get(fileToLoad.source)
      val handler = handlerFactory.getHandlerFor(fileToLoad)
      watcher.addToWatched(path, handler)
    })

    watcher.startWatching() //infinite loop
  }
}

