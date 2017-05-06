
val dottyVersion = "0.1.1-bin-20170502-df22149-NIGHTLY"
// or latest Nightly build
// val dottyVersion = dottyLatestNightlyBuild.get

val scala2Version = "2.12.2"

lazy val root = (project in file(".")).
  settings(
    name := "dotty-hello-world-cross",
    description := "Hello world sbt project that is cross compiled using Dotty and Scala 2",
    version := "0.1",

    // To make the REPL use Dotty
    scalaVersion := dottyVersion,

    // To cross compile with Dotty and Scala 2
    crossScalaVersions := Seq(dottyVersion, scala2Version)
  )
