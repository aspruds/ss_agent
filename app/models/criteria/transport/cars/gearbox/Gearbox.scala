package models.criteria.transport.cars.mileage

import models.criteria.CriteriaWithEnumValue
import models.criteria.transport.cars.gearbox.GearboxType.GearboxType

case class Gearbox(value: GearboxType) extends CriteriaWithEnumValue {
  override val param = "opt[35][]"
}
