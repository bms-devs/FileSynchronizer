package pl.com.bms.fileSynchronizer.config

import pl.com.bms.fileSynchronizer.config.Action.Action
import scopt.OptionParser

case class ArgumentsConfiguration(configFileName: String, action: Action)

class ArgumentsParser(args: Array[String]) {

  val parser: OptionParser[ArgumentsConfiguration] = new scopt.OptionParser[ArgumentsConfiguration]("FileUploader") {
    head("""
           |This program will help you with uploading files to the server.
           |It loads configuration from config.json file located in the execution
           |directory or any other file which name is supplied with -c
           |or --config argument.
           |
           |Example config.json:
           |{
           |  "connection": {
           |    "login": "login",
           |    "password": "password",
           |    "url": "host"
           |  },
           |  "sourceRoot": "/home/user/files",
           |  "destinationRoot": "/remoteHost/files",
           |  "files": [
           |    "fileToSynchronize"
           |  ],
           |  "directories": [
           |    "directoryToSynchronize"
           |  ]
           |}
           |
           |Above configuration will copy fileToSynchronize file and all files
           |from directoryToSynchronize directory into given destination.
           |If you want to upload files to Alfresco, all you need to do is set
           |destinationRoot to a node ref instead of a path.
         """.stripMargin)

    (opt[String]('c', "config") valueName "filename"
      text "Load application configuration from a file with a given name"
      action { (filename, config) => config.copy(configFileName = filename) })

    (opt[Unit]('w', "watch")
      text "Turn on watch mode. Given files will be monitored and uploaded in case of change"
      action { (_, config) => config.copy(action = Action.WATCH) })

    (opt[Unit]('W', "upload-and-watch")
      text "Upload all files first and then turn on watch mode"
      action { (_, config) => config.copy(action = Action.UPLOAD_AND_WATCH) })

    (help("help") abbr "h"
      text "Display this message")
  }

  private val defaultArgumentsConfiguration = ArgumentsConfiguration("config.json", Action.UPLOAD)
  private val argumentsConfiguration = parser.parse(args, defaultArgumentsConfiguration)

  if (!argumentsConfiguration.isDefined) System.exit(0)

  val configFileName = argumentsConfiguration.get.configFileName
  val action = argumentsConfiguration.get.action
}

object Action extends Enumeration {
  type Action = Value
  val SHOW_HELP, UPLOAD_AND_WATCH, WATCH, UPLOAD = Value
}