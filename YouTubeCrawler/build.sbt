name := "YouTubeCrawler"

version := "0.1"

scalaVersion := "2.13.1"

val jacksonVersion = "2.10.2"

libraryDependencies += "com.google.apis" % "google-api-services-youtube" % "v3-rev221-1.25.0"
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion
