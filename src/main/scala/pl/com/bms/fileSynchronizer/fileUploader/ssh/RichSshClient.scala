package pl.com.bms.fileSynchronizer.fileUploader.ssh

import com.decodified.scalassh.SshClient
import scala.language.implicitConversions

/**
 * @author bandrzejczak
 * @since 23.03.15
 */
class RichSshClient(val client:SshClient) {
  def recursivelyCreatePathIfNotExists(path: String) = client.exec("mkdir -p "+getParentPath(path))
  private def getParentPath(path: String) = if (path.contains('/')) path.substring(0,path.lastIndexOf('/')) else path
}

object RichSshClient {
  implicit def convertToRichSshClient(client: SshClient): RichSshClient = new RichSshClient(client)
}
