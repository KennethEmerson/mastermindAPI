package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import models.Code
import play.api.libs.json._


@Singleton
class ApiController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def ping = Action { implicit request =>
    Ok("Hello, Scala!")
  }

  def getSolution() = Action { implicit request => 
    val solution = Json.obj("solution" -> Code.getSolution)
    Ok(solution)
  }
  
  def evalGuess(guess: String) = Action { implicit request =>
    Ok(Json.toJson(Code.eval(Code.stringToArray(guess)))) 
  }
}