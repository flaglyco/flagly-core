lazy val `flagly-core` = (project in file(".")).settings(
  libraryDependencies ++= Seq(
    "dev.akif" % "e-gson" % "0.2.3-SNAPSHOT"
  )
)

description          in ThisBuild := "Core of Flagly API and SDKs"
homepage             in ThisBuild := Some(url("https://flagly.co"))
startYear            in ThisBuild := Some(2019)
licenses             in ThisBuild := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))
organization         in ThisBuild := "co.flagly"
organizationName     in ThisBuild := "Flagly"
organizationHomepage in ThisBuild := Some(url("https://flagly.co"))
developers           in ThisBuild := List(Developer("makiftutuncu", "Mehmet Akif Tütüncü", "m.akif.tutuncu@gmail.com", url("https://akif.dev")))
scmInfo              in ThisBuild := Some(ScmInfo(url("https://github.com/flaglyco/flagly-core"), "git@github.com:makiftutuncu/flagly-core.git"))

crossPaths              := false
autoScalaLibrary        := false
publishMavenStyle       := true
exportJars              := true
publishArtifact in Test := false
pomIncludeRepository    := { _ => false }
bintrayOrganization     := Some("flaglyco")
bintrayRepository       := "flagly-core"
