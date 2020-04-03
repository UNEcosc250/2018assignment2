lazy val root = (project in file(".")).
  settings(
    name := "Boids",
    version := "1.0",
    scalaVersion := "2.13.1"
  )



libraryDependencies += "org.scalafx" %% "scalafx" % "12.0.2-R18"
libraryDependencies += "org.typelevel"  %% "squants"  % "1.6.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"
