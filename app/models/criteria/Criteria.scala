package models.criteria

import org.jsoup.Connection

trait Criteria {
  def apply(connection: Connection)
}
