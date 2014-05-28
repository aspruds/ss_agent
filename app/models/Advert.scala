package models
import utils.HttpUtils

class Advert() {
	var description: String = _
	var imageUrl: String = _
	var url: String = _
	var year: Int = _
	var engineSize: String = _
	var mileage: Int = _
	var price: Int = _
	var currency: String = _

	def fullUrl: String = HttpUtils.url + url
}
