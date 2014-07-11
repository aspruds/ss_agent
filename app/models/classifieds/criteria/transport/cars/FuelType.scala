package models.classifieds.criteria.transport.cars

abstract sealed class FuelType

case class Diesel() extends FuelType

case class Petrol() extends FuelType

case class PetrolGas() extends FuelType
