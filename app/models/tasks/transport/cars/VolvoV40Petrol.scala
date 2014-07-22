package models.tasks.transport.cars

import models.tasks.Task
import models.criteria.ad.ToSell
import models.criteria.price.PriceMin
import models.criteria.transport.cars.fuel.Fuel
import models.criteria.transport.cars.fuel.FuelType._
import models.criteria.transport.cars.gearbox.GearboxType._
import models.criteria.transport.cars.mileage.{Gearbox, MileageMax}
import models.criteria.transport.cars.options.WithRails

case class VolvoV40Petrol() extends Task("volvo/v40", "Volvo V40 Petrol") {

  override def criterias = List(
    PriceMin(2000),
    MileageMax(200000),
    Fuel(Petrol),
    Gearbox(Manual),
    WithRails(),
    ToSell()
  )

}
