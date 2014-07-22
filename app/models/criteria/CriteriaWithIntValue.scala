package models.criteria

trait CriteriaWithIntValue extends CriteriaWithValue {
  def value: Int

  override def paramValue = value.toString
}
