import org.scalatest._
import org.scalatest.prop.TableDrivenPropertyChecks._
import models.Code

class CalculatorSpec extends FlatSpec with Matchers {
  "The Code object" should "generate an array of the correct length" in {
    Code.createNewGame(4,4)
    Code.getSolution.length should be (4)
  }

  it should "calculate the amount of correct placed colors" in {
    Code.createTestSolution(Array(1,2,3,3))
    Code.evaluateGuess(Array(1,2,3,4)) should be (Some(3,0)) 
  }

  it should "calculate the amount of correct colors incorrectly placed" in {
    Code.createTestSolution(Array(4,3,2,2))
    Code.evaluateGuess(Array(1,2,3,4)) should be (Some(0,3)) 
  }
  
  it should "correctly evaluate different guesses" in {
    Code.createTestSolution(Array(0,1,2,3))
    
    // table with possible tests, first row are column headers
    val guessesWithResults = Table(
      ("guess","evaluation"),
      (Array(0,1,2,3) , Some(4,0)),
      (Array(0,1,2,1) , Some(3,0)),
      (Array(0,1,3,1) , Some(2,1)),
      (Array(0,0,0,0) , Some(1,0))
    )
    
    forAll (guessesWithResults) {(guess:Array[Int], evaluation:Option[(Int,Int)]) => 
      Code.evaluateGuess(guess) should be (evaluation)
    }}

}