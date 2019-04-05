lazy val root = (project in file(".")).
  settings(
    name := "Boids",
    version := "1.0",
    scalaVersion := "2.12.8"
  )

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.144-R12"
libraryDependencies += "org.typelevel"  %% "squants"  % "1.3.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
