package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents,Request,AnyContent}
import models.Code
import play.api.libs.json._


@Singleton
class ApiController @Inject()(cc: ControllerComponents)
  extends AbstractController(cc) {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
  
  // handles the request for initiating a new game
  def newGame(reqAmount:Int, reqOptions:Int) = Action { implicit request =>
    Code.createNewGame(reqAmount,reqOptions)
    val startGame = Json.obj(
      "numberOfGuesses"  -> Code.getEvalCounter,
      "codeLength" -> Code.getCodeLength)
    Ok(startGame)
  }

  
  // handles the request for the solution
  def getSolution() = Action { implicit request => 
    val solution = Json.obj(
      "numberOfGuesses"  -> Code.getEvalCounter,  
      "solution" -> Code.getSolution)
    Ok(solution)
  }
  
  //function to transform inputstring to guess array
  def stringToArray(guess:String):Option[Array[Int]] = {
    try{
      Some(guess.split('.').map(_.toInt))
    }
    catch{
      case ex: NumberFormatException => None
    }
  }

  // handles the request for evaluating a guess
  def evalGuess(input: String) = Action { implicit request => 
    
    val guess = stringToArray(input) //will return None if input is invalid
    
    if(guess != None){
      val evalValues:Option[(Int,Int)] = Code.evaluateGuess(guess.get) 
    
      if(evalValues != None){
        val evaluation = Json.obj(
          "numberOfGuesses"     -> Code.getEvalCounter,
          "correct"     -> evalValues.get._1,
          "wrongPlace"  -> evalValues.get._2)
        Ok(evaluation)
      }
      else BadRequest("length of guess is different than code length") 
    }
    else BadRequest("guess is not valid")
  }

  // handles invalid requests
  def invalidRequest(error:String) = Action {
    BadRequest("Invalid Request")
  }
}