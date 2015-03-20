package pl.com.bms.fileSynchronizer.config

class ArgumentsParser(args: Array[String]) {
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