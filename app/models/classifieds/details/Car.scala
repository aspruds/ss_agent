package models.classifieds.details

case class Car(year: Int,
                      engineSize: String,
                      mileage: Option[Int]) extends AdDetails
