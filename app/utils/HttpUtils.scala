package utils

import models.tasks.Task
import models.criteria.Criteria
import org.jsoup._
import org.jsoup.nodes.Document
import play.api.Play.current
import play.api.cache.Cache

object HttpUtils {
  def fetchHtml(task: Task): Document = {
    val connection = Jsoup.connect(task.fullUrl)
    task.criterias.foreach {
      crit: Criteria => crit.apply(connection)
    }

    val cacheKey: String = task.fullUrl + connection.request.data.toString

    Cache.get(cacheKey) match {
      case Some(html) => {
        Jsoup.parse(html.toString)
      }
      case None => {
        val doc = connection.post
        Cache.set(cacheKey, doc.html())
        doc
      }
    }
  }
}
