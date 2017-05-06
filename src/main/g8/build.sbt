lazy val root = (project in file(".")).
  settings(
    // TODO Add cross compilation settings
    name := "dotty-hello-world-cross",
    description := "Hello world sbt project that is cross compiled using Dotty and Scala 2",
    version := "0.1",

    scalaVersion := "0.1.1-bin-20170502-df22149-NIGHTLY"
  )
