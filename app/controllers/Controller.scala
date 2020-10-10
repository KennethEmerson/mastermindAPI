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
  
  // handles the request for evaluating a guess
  def evalGuess(guess: String) = Action { implicit request => 
    val evalValues:Option[(Int,Int)] = Code.evaluateGuess(Code.stringToArray(guess)) 
    
    if(evalValues != None){
        val evaluation = Json.obj(
          "numberOfGuesses"     -> Code.getEvalCounter,
          "correct"     -> evalValues.get._1,
          "wrongPlace"  -> evalValues.get._2)
        Ok(evaluation)
    }
    else BadRequest("guess length does not match code length") 
  }

  def invalidRequest(error:String) = Action {
    BadRequest("Invalid Request")
  }
}