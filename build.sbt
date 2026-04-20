val scala3Version = "3.8.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-javalin",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.3.0" % Test,
      "io.javalin" % "javalin" % "7.2.0",
      "org.slf4j" % "slf4j-simple" % "2.0.17",
      "org.postgresql" % "postgresql" % "42.7.10"
    )
  )
