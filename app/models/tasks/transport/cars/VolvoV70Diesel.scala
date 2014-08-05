package models.tasks.transport.cars

import models.criteria.ad.ToSell
import models.criteria.price.PriceMax
import models.criteria.transport.cars.fuel.Fuel
import models.criteria.transport.cars.fuel.FuelType._
import models.criteria.transport.cars.gearbox.GearboxType._
import models.criteria.transport.cars.mileage.{Gearbox, MileageMax}
import models.criteria.transport.cars.options.WithRails
import models.criteria.year.{YearMax, YearMin}
import models.tasks.Task

case class VolvoV70Diesel() extends Task("volvo/v70", "Volvo V70") {

  override val criterias = List(
    YearMin(2007),
    Fuel(Diesel),
    Gearbox(Manual),
    ToSell()
  )

}
