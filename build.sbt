lazy val root = (project in file(".")).
  settings(
    name := "Boids",
    version := "2021.0",
    scalaVersion := "3.0.0-RC1"
  )

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "org.scalameta" %% "munit" % "0.7.22" % Test
testFrameworks += new TestFramework("munit.Framework")
