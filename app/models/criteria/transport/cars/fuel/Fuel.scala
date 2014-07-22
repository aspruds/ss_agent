package models.criteria.transport.cars.fuel

import models.criteria.CriteriaWithEnumValue
import models.criteria.transport.cars.fuel.FuelType.FuelType

case class Fuel(value: FuelType) extends CriteriaWithEnumValue {
  override val param = "opt[34][]"
}
