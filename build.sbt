organization in ThisBuild := "co.flagly"
version      in ThisBuild := "0.1.0-SNAPSHOT"

lazy val `flagly-core` = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "com.google.code.gson" % "gson" % "2.8.5"
  )
)
