package utils

import models.classifieds.details.Car
import models.classifieds.{Ad, Price}
import models.tasks.Task
import models.tasks.ui.TaskWithAds
import org.jsoup.nodes.Document

import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer

object AdParser {
  def parseHtml(task: Task, doc: Document): TaskWithAds = {
    val descriptions = doc.select("div.d1 a").map(_.text())
    val urls = doc.select("div.d1 a").map(_.attr("href"))
    val years = doc.select("tr[height=44] td:eq(3)").map(_.text())
    val engines = doc.select("tr[height=44] td:eq(4)").map(_.text())
    val mileages = doc.select("tr[height=44] td:eq(5)").map(_.text())
    val prices = doc.select("tr[height=44] td:eq(6)").map(_.text())
    val imageUrls = doc.select("td.msga2 img").map(_.attr("src"))

    val adverts = new ListBuffer[Ad[Car]]
    for (i <- 0 until descriptions.size) {
      val year = years(i)
      if (year != "-") {

        val ad = Ad[Car](
          description = descriptions(i),
          path = urls(i),
          url = task.baseUrl + urls(i),
          imageUrl = imageUrls(i),
          price = price(prices(i)),
          details = Car(
            year = years(i).toInt,
            engineSize = engines(i),
            mileage = mileage(mileages(i))
          )
        )
        adverts += ad
      }
    }

    // sort by price
    adverts.toList.sortBy(a => a.price.map(_.price))
    TaskWithAds(task, adverts)
  }

  /**
   * Extract mileage from text
   */
  private def mileage(text: String): Option[Int] = optionalText(text) {
    val mileageParts = text.split(" ")
    val mileage = mileageParts(0).toInt
    Some(mileage)
  }

  /**
   * Extract price,currency pair from text "100 EUR"
   */
  private def price(text: String): Option[Price] = optionalText(text) {
    val priceParts = text.split(" ")
    val price = priceParts(0).replace(",", "").toInt
    val currency = priceParts(1)
    Some(Price(price, currency))
  }

  private def optionalText[T](text: String)(transform: => Option[T]): Option[T] = {
    if (text.contains(" "))
      transform
    else
      None
  }
}
