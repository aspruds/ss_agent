package models.classifieds

import models.classifieds.details.AdDetails

case class Ad[A <: AdDetails](
                   description: String,
                   imageUrl: String,
                   path: String,
                   url: String,
                   price: Option[Price],
                   details: A
                   )
