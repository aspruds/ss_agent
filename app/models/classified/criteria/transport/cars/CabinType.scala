package models.classified.criteria.transport.cars

sealed abstract class CabinType

case class Estate() extends CabinType

case class Sedan() extends CabinType
