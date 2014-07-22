package controllers

import play.api.mvc._
import services.DefaultAdServiceComponent
import views.html.screens.{index => indexView}

object Application extends Controller with DefaultAdServiceComponent {

  def index = Action {
    Ok(indexView(adService.fetchAds))
  }
}
