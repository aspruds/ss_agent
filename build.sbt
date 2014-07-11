name := "ss_lv_parser"

version := "0.1-SNAPSHOT"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
  cache,
  "org.jsoup" % "jsoup" % "1.7.2"
)

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "bootstrap" % "3.1.1",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "bootstrap-glyphicons" % "bdd2cbfba0"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
