package pl.com.bms.fileSynchronizer

import java.nio.file.Paths

import pl.com.bms.fileSynchronizer.config.Configuration
import pl.com.bms.fileSynchronizer.fileUploader.TimePrinter
import pl.com.bms.fileSynchronizer.fileUploader.alfresco.AlfrescoFileUploader
import pl.com.bms.fileSynchronizer.fileUploader.ssh.SshFileUploader
import pl.com.bms.fileSynchronizer.watch.FileWatcher


class Application(config: Configuration) {

  val files = config.allFilesToLoad
  val uploaderWithTimePrinter = config.hasAlfrescoDestination match {
    case true => new AlfrescoFileUploader(config) with TimePrinter
    case false => new SshFileUploader(config) with TimePrinter
  }

  def upload() = {
    files.foreach(fileToLoad => {
      uploaderWithTimePrinter.uploadFile(fileToLoad)
    })
    println("All files uploaded!")
    this
  }


  def startWatching(): Unit = {
    val watcher = new FileWatcher(uploaderWithTimePrinter, config.sourceRoot)

    files map {
      file => Paths.get(config.sourceRoot+file)
    } foreach {
      path => watcher.addToWatched(path)
    }

    println("Watching...")
    watcher.startWatching() //infinite loop
  }
}

