package org.danielwojda.alfrescofileloader.config

class ArgumentsParser(args: Array[String]) {
  import Action._
  val action: Action = if(args.contains("-h") || args.contains("--help")) SHOW_HELP
                      else if (args.contains("-w") || args.contains("--watch")) UPLOAD_AND_WATCH
                      else UPLOAD
}

object Action extends Enumeration {
  type Action = Value
  val SHOW_HELP, UPLOAD_AND_WATCH, UPLOAD = Value
}