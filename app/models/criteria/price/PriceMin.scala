package models.criteria.price

import models.criteria.CriteriaWithIntValue

case class PriceMin(value: Int) extends CriteriaWithIntValue {
  override val param = "topt[8][min]"
}
