package models.criteria

trait CriteriaWithEnumValue extends CriteriaWithValue {
  def value: Any

  override def paramValue = value.toString
}
