lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.truecaller",
      scalaVersion := "2.12.2",
      version      := "0.1"
    )),
    name := "truechess"
  )
