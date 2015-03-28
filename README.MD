# FileSynchronizer

[![Build Status](https://travis-ci.org/bms-devs/FileSynchronizer.svg?branch=master)](https://travis-ci.org/bms-devs/FileSynchronizer) [![Coverage Status](https://coveralls.io/repos/bms-devs/FileSynchronizer/badge.svg?branch=master)](https://coveralls.io/r/bms-devs/FileSynchronizer?branch=master) 

### File synchronization tool for ssh and <a href="http://www.alfresco.com/"><img src="http://www.alfresco.com/sites/www.alfresco.com/themes/alfrescodotcom/img/logo.svg" alt="Alfresco" height="24px"></a>

------------------------------------------------

## Purpose

This little app helps you with keeping your local files synchronized with remote files available either through ssh protocol or through Alfresco API. We use it mainly during development of other apps, but you could use to synchronize whatever you want.

## Prerequisites

#### Building

To build the application you need [sbt](http://www.scala-sbt.org/). You can download it from [http://www.scala-sbt.org/download.html](http://www.scala-sbt.org/download.html) website, or you can just find sbt in your favourite package manager (`apt-get`, `yum` or other).

Application works on JVM, so you still need to have Java Development Kit (JDK) installed. Application should compile on JDK version 1.7+, that can be found [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

#### Runing

To run the application you just need to install JRE version 1.7+. If you don't have it go to [download page](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html).

## Running tests

Application tests are using [ScalaTest](http://www.scalatest.org/). It's fully integrated with sbt, so to run all tests write:

```bash
sbt test
```

## Building

To build fat-jar with all the application's dependencies we're using [sbt-assembly](https://github.com/sbt/sbt-assembly) plugin. The usage is pretty straightforward. To build the app you just need to write:

```bash
sbt assembly
```

## Configuring

The application's configuration is placed in `config.json` file. Here's the purpose of all application's properites:

* `connection`
  * `url` - Host url (either ssh host or Alfresco host and port in `http://host:port/` format)
  * `login` - User's login
  * `password` - User's password
* `sourceRoot` - path of local directory inside which all the synchronized file will be located. This directory files and directories specified in next properties will be synchronized with remote host
* `destinationRoot` - path of remote directory (ssh) or node ref (Alfresco) of folder which can be mapped to sourceRoot local directory
* `files` - list of all the file to keep synchronized
* `directories` - list of all the directories to keep synchronized

Here is the example configuration for ssh host:

```json
{
  "connection": {
    "login": "root",
    "password": "password123",
    "url": "192.168.253.177"
  },
  "sourceRoot": "/home/bms/project/",
  "destinationRoot": "/opt/tomcat/webapps/project/",
  "files": [
    "WEB-INF/main.jsp"
  ],
  "directories": [
    "resources/js/"
  ]
}

```

And here is the example configuration for Alfresco repository:

```json
{
  "connection": {
    "login": "admin",
    "password": "admin",
    "url": "http://localhost:8080"
  },
  "sourceRoot": "/home/bms/project/scripts/",
  "destinationRoot": "workspace://SpacesStore/17855f47-ef83-4eeb-b9f0-a41f07ab6cac",
  "files": [
    "start.js"
  ],
  "directories": [
    "controllers/"
  ]
}
```

## Running

To run the application you have to execute jar which after build process will be available in `target/scala-2.11/` folder. The jar filename will have `FileSynchronizer-assembly-{version}.jar` format. To run it you have to execute following command:

```bash
java -jar target/scala-2.11/FileSynchronizer-assembly-{version}.jar {additional options}
```

Here is the table of all additional options available:

| Option                 | Effect                                                                            |
|:-----------------------|:----------------------------------------------------------------------------------|
| -h, --help             | Display help message                                                              |
| -w, --watch            | Turn on watch mode. Given files will be monitored and uploaded in case of change  |
| -W, --upload-and-watch | Upload all files first and then turn on watch mode                                |
