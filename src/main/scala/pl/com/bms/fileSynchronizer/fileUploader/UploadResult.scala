package pl.com.bms.fileSynchronizer.fileUploader

/**
 * @author bandrzejczak
 * @since 17.03.15
 */
case class UploadResult(status: String, message: Option[String] = None){
  override def toString: String = {
    "status = " + status + message.map(s => "; message = " + s).getOrElse("")
  }
}
