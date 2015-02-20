package org.danielwojda.alfrescofileloader

object HelpPage {
  def show(): Unit = println(
    """
      |This program will help you with uploading files into Alfresco repository.
      |It loads configuration from config.json file. You must create this file in the same directory as the program.
      |
      |Example config.json:
      |{
      |  "connection": {
      |    "login": "admin",
      |    "password": "admin",
      |    "alfrescoUrl": "http://localhost:8080"
      |  },
      |  "files": [
      |    {
      |      "source": "/home/user/projects/scripts/start.js",
      |      "destination": "workspace://SpacesStore/17855f47-ef83-4eeb-b9f0-a41f07ab6cac"
      |    }
      |  ],
      |  "directories": [
      |     {
      |      "source": "/home/user/projects/scripts/server",
      |      "destination": "workspace://SpacesStore/17855f47-ef83-4eeb-b9f0-a41f07ab6cac"
      |    }
      |  ]
      |}
      |
      |Above configuration will copy start.js file and all files (NOT recursively) from server directory into given destination.
      |
      |Additional options:
      | -w, --watch    Turn on watch mode. Given files will be monitored and uploaded in case of change
      |
    """.stripMargin)

}
