package pl.com.bms.fileSynchronizer.fileUploader

import java.text.SimpleDateFormat
import java.util.Date

class TimePrinter(val fileUploader: FileUploader) extends FileUploader{
  
  val dateFormat = new SimpleDateFormat("HH:mm:ss")
  
  override def uploadFile(fileToLoad: String): UploadResult = {
    val response = fileUploader.uploadFile(fileToLoad)
    println(dateFormat.format(new Date()) + ": File: " + fileToLoad + " result: " + response)
    response
  }
}
