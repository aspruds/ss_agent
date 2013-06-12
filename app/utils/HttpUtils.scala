package com.spruds.ss

import org.jsoup._
import scala.math._
import scala.collection.JavaConversions._
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.TreeMap

object HttpUtils {
	var url = "http://www.ss.lv"

    abstract class FuelType
    case class Diesel() extends FuelType
    case class Petrol() extends FuelType
    case class PetrolGas() extends FuelType

    abstract class CabinType
    case class Estate() extends CabinType
    case class Sedan() extends CabinType

    abstract class GearboxType
    case class Automatic() extends GearboxType
    case class Manual() extends GearboxType

	class Task(theUrl: String, theName: String) {
		var url: String = theUrl
		var name: String = theName
		var priceMin: Option[Int] = None
		var priceMax: Option[Int] = None
		var mileageMin: Option[Int] = None
		var mileageMax: Option[Int] = None
		var fuel: FuelType = _
		var cabin: CabinType = _
		var gearbox: GearboxType = _
		var rails: Boolean = false

		override def toString =	"[Task: url=" + url + " name=" + name + "]"
		override def clone = this
	}

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
	
	def tasks: List[Task] = {
	    val saab93Petrol = new Task(url + "/lv/transport/cars/saab/9-3/search-result/", "SAAB 9-3 Petrol")
	    saab93Petrol.fuel = new Petrol
	    saab93Petrol.cabin = new Estate
        saab93Petrol.gearbox = new Manual
		saab93Petrol.rails = true
		
	    val saab93Diesel = new Task(url + "/lv/transport/cars/saab/9-3/search-result/", "SAAB 9-3 Diesel")
	    saab93Diesel.fuel = new Diesel
	    saab93Diesel.cabin = new Estate
        saab93Diesel.gearbox = new Manual
        saab93Diesel.rails = true

	    val saab95Petrol = new Task(url + "/lv/transport/cars/saab/9-5/search-result/", "SAAB 9-5 Petrol")
	    saab95Petrol.fuel = new Petrol
	    saab95Petrol.cabin = new Estate
	    saab95Petrol.gearbox = new Manual
	    saab95Petrol.mileageMax = Some(220000)
	    saab95Petrol.rails = true
	    
	    val saab95LPG = new Task(url + "/lv/transport/cars/saab/9-5/search-result/", "SAAB 9-5 LPG")
	    saab95LPG.fuel = new PetrolGas
	    saab95LPG.cabin = new Estate
	    saab95LPG.gearbox = new Manual
	    saab95LPG.mileageMax = Some(220000)
	    saab95LPG.rails = true	    

	    val volvoV40Petrol = new Task(url + "/lv/transport/cars/volvo/v40/search-result/", "Volvo V40 Petrol")
        volvoV40Petrol.priceMin = Some(2000)
        volvoV40Petrol.mileageMax = Some(200000)
        volvoV40Petrol.fuel = new Petrol
        volvoV40Petrol.gearbox = new Manual
        volvoV40Petrol.rails = true

	    val volvoV50 = new Task(url + "/lv/transport/cars/volvo/v50/search-result/", "Volvo V50")
        volvoV50.mileageMax = Some(200000)
        volvoV50.gearbox = new Manual
		volvoV50.rails = true
		volvoV50.priceMax = Some(6000)
		
        List(
            saab93Petrol,
            saab93Diesel,
            saab95Petrol,
            saab95LPG,
            volvoV40Petrol,
            volvoV50
        )
	}
	
	def parseTasks: List[(Task, List[Advert])] = {
		for(t <- tasks) yield (t -> fetchAdverts(t))
	}
	
	def fetchAdverts(task: Task): List[Advert] = {
	    val connection = Jsoup.connect(task.url);
		
	    // handle fuel type
	    val fuelTypeId = task.fuel match {
	        case _ : Petrol => "493"
	        case _ : PetrolGas => "495"
	        case _: Diesel => "494"
	        case _ => null
	    }
	    if(fuelTypeId != null) connection.data("opt[34][]", fuelTypeId)

        // handle cabin type
	    val cabinTypeId = task.cabin match {
	        case _ : Estate => "483"
	        case _ : Sedan => "484"
	        case _ => null
	    }
	    if(cabinTypeId != null) connection.data("opt[32][]", cabinTypeId)

        // handle gearbox type
	    val gearboxTypeId = task.gearbox match {
	        case _ : Automatic => "497"
	        case _ : Manual => "496"
	        case _ => null
	    }
	    if(gearboxTypeId != null) connection.data("opt[35][]", gearboxTypeId)

        if(!task.priceMin.isEmpty) connection.data("topt[8][min]", task.priceMin.get.toString)
        if(!task.priceMax.isEmpty) connection.data("topt[8][max]", task.priceMax.get.toString)

        if(!task.mileageMin.isEmpty) connection.data("topt[16][min]", task.mileageMin.get.toString)
        if(!task.mileageMax.isEmpty) connection.data("topt[16][max]", task.mileageMax.get.toString)

        // to sell only
        connection.data("sid", "1")
        
        if(task.rails) {
			connection.data("checkbox[961]", "1")
			connection.data("opt[961][]", "17311")
		}
		
		val doc = connection.post()
		
		println(doc)

		val descriptions = doc.select("div.d1 a").map(_.text())
        val urls = doc.select("div.d1 a").map(_.attr("href"))
        val years = doc.select("tr[height=44] td:eq(3)").map(_.text())
        val engines = doc.select("tr[height=44] td:eq(4)").map(_.text())
        val mileages = doc.select("tr[height=44] td:eq(5)").map(_.text())
        val prices = doc.select("tr[height=44] td:eq(6)").map(_.text())
        val imageUrls = doc.select("td.msga2 img").map(_.attr("src"))

        val adverts = new ListBuffer[Advert]
        for(i <- 0 until descriptions.size) {
            val year = years(i)
            if(year != "-") {
                val ad = new Advert
                ad.description = descriptions(i)
                ad.url = urls(i)
                ad.year = years(i).toInt
                ad.engineSize = engines(i)
                ad.imageUrl = imageUrls(i)

                var mileage = mileages(i)
                if(mileage.contains(" ")) {
                    var mileageParts = mileage.split(" ")
                    ad.mileage = mileageParts(0).toInt
                }

                var price = prices(i)
                if(price.contains(" ")) {
                    var priceParts = price.split(" ")
                    ad.price = priceParts(0).replace(",", "").toInt
                    ad.currency = priceParts(1)
                }
                adverts += ad
            }
        }
        adverts.toList.sortBy(_.price)
	}
}