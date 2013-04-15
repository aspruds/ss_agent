package controllers

import play.api._
import play.api.mvc._
import com.spruds.ss._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index(HttpUtils.parseTasks))
  }
}