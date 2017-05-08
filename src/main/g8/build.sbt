
val defaultDottyVersion = "0.1.1-bin-20170502-df22149-NIGHTLY" // TODO: put technical release version
val defaultScala2Version = "2.12.2"

val useLatestDottyNightly = settingKey[Boolean]("Use the latest Dotty nightly build.")

val dottyVersion = settingKey[String]("Dotty version used as scalaVersion and in cross compilation.")
val scala2Version = settingKey[String]("Scala 2 version used in cross compilation.")

lazy val root = (project in file(".")).
  settings(
    name := "dotty-cross-template",
    description := "Template sbt project that is cross compiled using Dotty and Scala 2",
    version := "0.1",

    useLatestDottyNightly := false,

    dottyVersion := {
      if (useLatestDottyNightly.value) dottyLatestNightlyBuild.get
      else defaultDottyVersion
    },

    scala2Version := defaultScala2Version,

    // To make the default compiler and REPL use Dotty
    scalaVersion := dottyVersion.value,

    // To cross compile with Dotty and Scala 2
    crossScalaVersions := Seq(dottyVersion.value, scala2Version.value)
  )
