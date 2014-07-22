package models.criteria.transport.cars.cabin

import models.criteria.CriteriaWithEnumValue
import models.criteria.transport.cars.cabin.CabinType.CabinType

case class Cabin(value: CabinType) extends CriteriaWithEnumValue {
  override val param = "opt[32][]"
}
