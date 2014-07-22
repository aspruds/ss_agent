package models.tasks.transport.cars

import models.tasks.Task
import models.criteria.ad.ToSell
import models.criteria.price.PriceMax
import models.criteria.transport.cars.cabin.Cabin
import models.criteria.transport.cars.cabin.CabinType._
import models.criteria.transport.cars.fuel.Fuel
import models.criteria.transport.cars.fuel.FuelType._
import models.criteria.transport.cars.gearbox.GearboxType._
import models.criteria.transport.cars.mileage.Gearbox
import models.criteria.transport.cars.options.WithRails

case class Saab93Diesel() extends Task("saab/9-3", "SAAB 9-3 Diesel") {

  override def criterias = List(
      PriceMax(5700),
      Fuel(Diesel),
      Cabin(Estate),
      Gearbox(Manual),
      WithRails(),
      ToSell()
    )

}
