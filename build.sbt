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
    "org.scalaj" % "scalaj-http_2.10" % "1.1.0",
    "org.specs2" %% "specs2" % "2.2.3" % "test"
  )
}
