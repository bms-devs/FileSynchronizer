name := "AlfrescoFileLoader"

organization := "org.danielwojda"

version := "0.1"

scalaVersion := "2.11.5"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies += "io.spray" %% "spray-json" % "1.3.1"
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.4"
libraryDependencies += "org.apache.httpcomponents" % "httpmime" % "4.4"
libraryDependencies += "org.apache.httpcomponents" % "fluent-hc" % "4.4"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.3.0-SNAP2" % "test"

