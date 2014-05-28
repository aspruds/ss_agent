package controllers

import play.api.mvc._
import utils.HttpUtils

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(HttpUtils.parseTasks))
  }
}
