organization in ThisBuild := "co.flagly"
version      in ThisBuild := "0.1.1-SNAPSHOT"

lazy val `flagly-core` = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "com.google.code.gson" % "gson" % "2.8.5"
  )
)

description  := "Core of Flagly API and SDKs"
homepage     := Some(url("https://flagly.co"))
startYear    := Some(2019)
licenses     := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))
scmInfo      := Some(ScmInfo(url("https://github.com/flaglyco/flagly-core"), "https://github.com/flaglyco/flagly-core"))
developers   := List(Developer("makiftutuncu", "Mehmet Akif Tütüncü", "m.akif.tutuncu@gmail.com", url("https://makiftutuncu.wordpress.com")))

crossPaths := false
publishMavenStyle := true
exportJars := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
bintrayOrganization := Some("flaglyco")
bintrayRepository := "flagly-core",
