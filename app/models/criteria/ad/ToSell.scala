package models.criteria.ad

import models.criteria.Criteria
import org.jsoup.Connection

case class ToSell() extends Criteria {
  override def apply(connection: Connection) = {
    connection.data("sid", "1")
  }
}
