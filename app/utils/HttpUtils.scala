package utils

import models.tasks.Task
import models.criteria.Criteria
import org.jsoup._
import org.jsoup.nodes.Document
import play.api.Play.current
import play.api.cache.Cache

object HttpUtils extends Logging {
  private def applyCriterias(connection: Connection, task: Task) = task.criterias.foreach {
    crit: Criteria => crit.apply(connection)
  }

  private def cacheKey(connection: Connection): String = {
    val req = connection.request
    req.url + req.data.toString
  }

  private def execute(connection: Connection): Document = {
    logger.debug(s"fetching url: ${connection.request.url}")
    connection.post
  }

  def fetchHtml(task: Task): Document = {
    val connection = Jsoup.connect(task.fullUrl)
    applyCriterias(connection, task)

    val key: String = cacheKey(connection)

    Cache.get(key) match {
      case Some(html) => {
        Jsoup.parse(html.toString)
      }
      case None => {
        val doc = execute(connection)
        Cache.set(key, doc.html())
        doc
      }
    }
  }

}
