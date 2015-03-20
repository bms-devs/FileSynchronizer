package pl.com.bms.fileSynchronizer

import pl.com.bms.fileSynchronizer.fileUploader.FileUploader

class HandlerFactory(val uploader: FileUploader) {

  def getHandlerFor(file: String) = () => uploader.uploadFile(file)

}
