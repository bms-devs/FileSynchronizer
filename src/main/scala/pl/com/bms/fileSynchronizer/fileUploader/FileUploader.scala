package pl.com.bms.fileSynchronizer.fileUploader

trait FileUploader {
  def uploadFile(fileToLoad: String): UploadResult
}
