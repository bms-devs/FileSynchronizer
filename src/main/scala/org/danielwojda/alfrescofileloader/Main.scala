package org.danielwojda.alfrescofileloader

import java.nio.file.Paths

import org.danielwojda.alfrescofileloader.config.{FileToLoad, Configuration}
import org.danielwojda.alfrescofileloader.fileuploader.FileUploader
import org.danielwojda.alfrescofileloader.watch.FileWatcher


object Main {

  def main(args: Array[String]) {
    val config = Configuration.load()
    val files = config.files
    val uploader = new FileUploader(config.connection)
    
    files.foreach(fileToLoad => {
      uploader.uploadFile(fileToLoad)
    })
    
    if(watchModeIsOn(args)) {
      startWatching(files, uploader)
    }

  }

  def watchModeIsOn(args: Array[String]): Boolean = {
    args.contains("-w") || args.contains("--watch")
  }

  def startWatching(files: List[FileToLoad], uploader: FileUploader): Unit = {
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
