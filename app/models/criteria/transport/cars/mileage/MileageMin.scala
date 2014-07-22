package models.criteria.transport.cars.mileage

import models.criteria.CriteriaWithIntValue

case class MileageMin(value: Int) extends CriteriaWithIntValue {
  override val param = "topt[16][min]"
}
