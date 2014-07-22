package models.criteria.transport.cars.options

import models.criteria.Criteria
import org.jsoup.Connection

case class WithRails() extends Criteria {
  override def apply(connection: Connection) = {
    connection.data("checkbox[961]", "1")
    connection.data("opt[961][]", "17311")
  }
}
