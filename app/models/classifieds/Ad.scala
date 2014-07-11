package models.classifieds

import models.classifieds.details.AdDetails
import utils.HttpUtils

case class Ad[A <: AdDetails](
                   description: String,
                   imageUrl: String,
                   url: String,
                   price: Option[Price],
                   details: A
                   ) {

  def fullUrl: String = HttpUtils.url + url
}
