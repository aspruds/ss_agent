name := "ss_lv_parser"

version := "0.1-SNAPSHOT"

scalacOptions ++= Seq("-deprecation", "-feature")

libraryDependencies ++= Seq(
	cache,
	"org.jsoup" % "jsoup" % "1.7.2"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)
