name := "YouTubeCrawler"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "com.google.apis" % "google-api-services-youtube" % "v3-rev221-1.25.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % "2.10.2"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.2"
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.2"
