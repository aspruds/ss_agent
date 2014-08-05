package models.criteria.year

import models.criteria.CriteriaWithIntValue

case class YearMin(value: Int) extends CriteriaWithIntValue {
  override val param = "topt[18][min]"
}
