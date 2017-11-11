lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.lamedh",
      scalaVersion := "2.12.3",
      version      := "0.5"
    )),
    name := "hamiltonian"
  )
