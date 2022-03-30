lazy val root = (project in file(".")).
  settings(
    name := "Boids",
    version := "2022.0",
    scalaVersion := "3.1.0"
  )

libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
testFrameworks += new TestFramework("munit.Framework")
