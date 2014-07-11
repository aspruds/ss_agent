package controllers

import play.api.mvc._
import utils.HttpUtils
import views.html.screens.{index => indexView}

object Application extends Controller {

  def index = Action {
    val tasks = HttpUtils.parseTasks
    Ok(indexView(tasks))
  }
}
