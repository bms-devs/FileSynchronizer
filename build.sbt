name := "AlfrescoFileLoader"

organization := "org.danielwojda"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  Seq(
    "io.spray" %% "spray-json" % "1.2.5",
    "org.apache.httpcomponents" % "httpclient" % "4.4",
    "org.apache.httpcomponents" % "httpmime" % "4.4",
    "org.apache.httpcomponents" % "fluent-hc" % "4.4"
  )
}
