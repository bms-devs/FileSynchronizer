package org.danielwojda.alfrescofileloader.watch

import java.nio.file.StandardWatchEventKinds._
import java.nio.file._
import scala.collection.JavaConversions._


class FileWatcher {
  val watchedFiles = collection.mutable.Map[String, () => Unit]()

  def addToWatched(filePath: Path, handler: () => Unit):  Unit = {
    val key = filePath.toAbsolutePath.toString
    watchedFiles.put(key, handler)
  }


  def startWatching() = {
    val watcher: WatchService = FileSystems.getDefault.newWatchService()
    watchedFiles.keys foreach (path => Paths.get(path).getParent.register(watcher, ENTRY_MODIFY))
    
    while(true) {
      val key = watcher.take()
      goSleepToAvoidEventDuplication()
      key.pollEvents() foreach {
        event =>
          val relativePath = event.context().asInstanceOf[Path]
          val path = key.watchable().asInstanceOf[Path].resolve(relativePath)
          event.kind() match {
            case ENTRY_MODIFY =>
              notifyHandlerAssignedTo(path)
            case x =>
              println(s"Unexpected event $x")
          }
      }
      
      key.reset()
    }
  }


  /*
  More info: http://stackoverflow.com/questions/16777869/java-7-watchservice-ignoring-multiple-occurrences-of-the-same-event
   */
  private def goSleepToAvoidEventDuplication() = Thread.sleep(100)


  def notifyHandlerAssignedTo(path: Path) = {
    watchedFiles.get(path.toString) foreach {
      handler => handler()
    }
  }
  
}
