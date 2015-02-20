package org.danielwojda.alfrescofileloader.fileuploader
import org.danielwojda.alfrescofileloader.config.FileToLoad

trait FileUploader {

  def uploadFile(fileToLoad: FileToLoad): Int
}
