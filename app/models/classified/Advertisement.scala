package models.classified

import utils.HttpUtils

case class Advertisement(
                   description: String,
                   imageUrl: String,
                   url: String,
                   year: Int,
                   engineSize: String,
                   mileage: Option[Int],
                   price: Option[Price]
                   ) {

  def fullUrl: String = HttpUtils.url + url
}
