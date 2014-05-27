import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "ss_lv_parser"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.jsoup" % "jsoup" % "1.7.2",
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
        templatesImport += "com.spruds.ss.model._"
  )
}
