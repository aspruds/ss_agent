package models.tasks.transport.cars

import models.tasks.Task
import models.criteria.ad.ToSell
import models.criteria.price.PriceMax
import models.criteria.transport.cars.gearbox.GearboxType._
import models.criteria.transport.cars.mileage.{Gearbox, MileageMax}
import models.criteria.transport.cars.options.WithRails

case class VolvoV50() extends Task("volvo/v50", "Volvo V50") {

  override val criterias = List(
    MileageMax(200000),
    Gearbox(Manual),
    WithRails(),
    PriceMax(5700),
    ToSell()
  )

}
