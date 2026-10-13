// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
// sbt 2.0.10 is published for Scala 3.8.4. scripted-sbt is not published for 2.12.
lazy val scalaVersions = Seq("3.8.4")
lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-cross-template",
    scalaVersion := scalaVersions.head,
    Test / test := {
      val _ = (Test / g8Test).toTask("").value
      sbt.protocol.testing.TestResult.Passed
    },
    // giter8 copies ScriptedPlugin project settings, not the global default for this key.
    sbt.ScriptedPlugin.autoImport.scriptedKeepTempDirectory := false,
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    crossScalaVersions := scalaVersions
  )

ThisBuild / githubWorkflowJavaVersions := Seq("17", "21", "25").map(JavaSpec.temurin)
ThisBuild / githubWorkflowScalaVersions := scalaVersions
ThisBuild / githubWorkflowBuildPostamble := Seq(
  // This runs the template with the default parameters, and runs test within the templated app.
  WorkflowStep.Run(List("sbt -Dfile.encoding=UTF8 -J-XX:ReservedCodeCacheSize=256M test")),
  WorkflowStep.Run(List(
    s"pushd target/out/jvm/scala-${scalaVersions.head}/scala3-cross-template/sbt-test/scala3-cross-template/scripted && sbt '+run; +test' && popd"
  )),
)
ThisBuild / githubWorkflowPublishTargetBranches := Nil
Global / onChangedBuildSource := ReloadOnSourceChanges
