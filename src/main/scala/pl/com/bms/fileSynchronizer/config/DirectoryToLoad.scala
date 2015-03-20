package pl.com.bms.fileSynchronizer.config

import java.io.File
import java.nio.file.{NoSuchFileException, Paths}

case class DirectoryToLoad(sourceRoot: String, path: String) {

  def filesToLoad(): List[String] = {
    val absolutePath = sourceRoot + path
    val rootDirectory = findDirectory(absolutePath) getOrElse {
      throw new NoSuchFileException(absolutePath)
    }
    deepListFiles(rootDirectory)
      .map(file => Paths.get(sourceRoot).relativize(file.toPath).toString)
  }
  
  def findDirectory(path: String): Option[java.io.File] = {
    val directory = new File(path)
    if(directory.exists) Some(directory) else None
  }


  def deepListFiles(file: java.io.File) : List[java.io.File] = {
    file
      .listFiles
      .flatMap(file => if(file.isDirectory) file :: deepListFiles(file) else List(file))
      .toList
  }

}







