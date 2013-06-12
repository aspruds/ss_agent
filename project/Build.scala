import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "ss_agent"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    "org.jsoup" % "jsoup" % "1.7.2",
    jdbc,
    anorm
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
        templatesImport += "com.spruds.ss.model._"
  )
}
