package models.tasks.transport.cars

import models.tasks.Task
import models.criteria.ad.ToSell
import models.criteria.transport.cars.cabin.Cabin
import models.criteria.transport.cars.cabin.CabinType._
import models.criteria.transport.cars.fuel.Fuel
import models.criteria.transport.cars.fuel.FuelType._
import models.criteria.transport.cars.gearbox.GearboxType._
import models.criteria.transport.cars.mileage.{Gearbox, MileageMax}
import models.criteria.transport.cars.options.WithRails


case class Saab95Petrol() extends Task("saab/9-5", "SAAB 9-5 Petrol") {

  override val criterias = List(
    Fuel(Petrol),
    Cabin(Estate),
    Gearbox(Manual),
    MileageMax(220000),
    WithRails(),
    ToSell()
  )

}
