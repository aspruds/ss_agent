package models.criteria

import org.jsoup.Connection

trait CriteriaWithValue extends Criteria {
  def paramValue: String

  def param: String

  override def apply(connection: Connection) = {
    connection.data(param, paramValue)
  }
}
