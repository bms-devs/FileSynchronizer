package pl.com.bms.fileSynchronizer

object HelpPage {
  def show(): Unit = println(
    """
      |This program will help you with uploading files to the server.
      |It loads configuration from config.json file located in execution
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
      |  "sourceRoot": "/home/user/files/",
      |  "destinationRoot": "/remoteHost/files/",
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
      |destinationRoot, to a node ref instead of path.
      |
      |Additional options:
      | -h, --help               Display this message
      | -w, --watch              Turn on watch mode. Given files will be monitored and uploaded in case of change
      | -W, --upload-and-watch   Upload all files first and then turn on watch mode
      | -c, --config {filename}  Load application configuration from a file with a given name
    """.stripMargin)

}
