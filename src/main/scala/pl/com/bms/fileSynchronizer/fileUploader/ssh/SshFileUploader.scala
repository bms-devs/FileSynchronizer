package pl.com.bms.fileSynchronizer.fileUploader.ssh

import com.decodified.scalassh.HostKeyVerifiers.DontVerify
import com.decodified.scalassh.SSH.Result
import com.decodified.scalassh._
import pl.com.bms.fileSynchronizer.config.Configuration
import pl.com.bms.fileSynchronizer.fileUploader.{FileUploader, UploadResult}
import pl.com.bms.fileSynchronizer.fileUploader.ssh.RichSshClient._

/**
 * @author bandrzejczak
 * @since 17.03.15
 */

class SshFileUploader(val config: Configuration) extends FileUploader {

  val connection = SSH(config.connection.url, configProvider)(_: SshClient => Result[Unit])

  private def configProvider = new HostConfigProvider{
    override def apply(v1: String): Validated[HostConfig] = {
      try {
        Right(
          HostConfig(
            login = PasswordLogin(config.connection.login, SimplePasswordProducer(config.connection.password)),
            hostName = config.connection.url,
            hostKeyVerifier = DontVerify
          )
        )
      } catch {
        case _:Throwable => Left(v1)
      }
    }
  }

  override def uploadFile(fileToLoad: String):UploadResult = {
    val source = config.sourceRoot + fileToLoad
    val destination = config.destinationRoot + fileToLoad
    uploadFile(source, destination)
  }

  private def uploadFile(source: String, destination: String): UploadResult = {
    connection { client =>
      client.recursivelyCreatePathIfNotExists(destination)
      client.upload(source, destination)
    } match {
      case Left(error) => UploadResult("error", Some(error.toString))
      case Right(_) => UploadResult("success")
    }
  }
}


