package org.danielwojda.alfrescofileloader.fileuploader

import java.text.SimpleDateFormat
import java.util.Date

import org.danielwojda.alfrescofileloader.config.FileToLoad

class TimePrinter(val fileUploader: FileUploader) extends FileUploader{
  
  val dateFormat = new SimpleDateFormat("HH:mm:ss")
  
  override def uploadFile(fileToLoad: FileToLoad): Int = {
    val response = fileUploader.uploadFile(fileToLoad)
    println(dateFormat.format(new Date()) + ": File: " + fileToLoad.source + " status:" + response)
    response
  }
}
