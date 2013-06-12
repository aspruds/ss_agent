package com.spruds.ss.model

class Task(thePath: String, theName: String) {
	var url: String = "/lv/transport/cars/" + thePath + "/search-result/";
	var name: String = theName
	var priceMin: Option[Int] = None
	var priceMax: Option[Int] = None
	var mileageMin: Option[Int] = None
	var mileageMax: Option[Int] = None
	var fuel: FuelType = _
	var cabin: CabinType = _
	var gearbox: GearboxType = _
	var rails: Boolean = false
	var model: String = _
	var brand: String = _

	override def toString =	"[Task: url=" + url + " name=" + name + "]"
	override def clone = this
}