package pl.com.bms.fileSynchronizer.fileUploader

import java.text.SimpleDateFormat
import java.util.Date

trait TimePrinter extends FileUploader {

  abstract override def uploadFile(fileToLoad: String): UploadResult = {
    val dateFormat = new SimpleDateFormat("HH:mm:ss")
    val response = super.uploadFile(fileToLoad)
    println(dateFormat.format(new Date()) + ": File: " + fileToLoad + " result: " + response)
    response
  }
}
