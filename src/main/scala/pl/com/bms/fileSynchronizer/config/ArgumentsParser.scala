package pl.com.bms.fileSynchronizer.config

class ArgumentsParser(args: Array[String]) {
  private val DEFAULT_CONFIG_FILE_NAME = "config.json"

  val configFileName = providedConfigFileName getOrElse DEFAULT_CONFIG_FILE_NAME

  private def providedConfigFileName = {
    val configFileNamePosition = configOptionPosition + 1
    val argumentsCount = args.length
    configFileNamePosition match {
      case 0 => None
      case `argumentsCount` => throw new NoConfigFileNameException
      case n => Some(args(n))
    }
  }

  private def configOptionPosition = {
    if (args.contains("--config")) args.indexOf("--config") else args.indexOf("-c")
  }

  import pl.com.bms.fileSynchronizer.config.Action._
  val action: Action = if(args.contains("-h") || args.contains("--help")) SHOW_HELP
                      else if (args.contains("-W") || args.contains("--upload-and-watch")) UPLOAD_AND_WATCH
                      else if (args.contains("-w") || args.contains("--watch")) WATCH
                      else UPLOAD
}

object Action extends Enumeration {
  type Action = Value
  val SHOW_HELP, UPLOAD_AND_WATCH, WATCH, UPLOAD = Value
}