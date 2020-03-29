import org.scalatest._
import models.Code
class CalculatorSpec extends FlatSpec with Matchers {
  "The Code object" should "generate an array of the correct length" in {
    Code.create(4,4)
    Code.giveSolution.length should be (4)
  }

  it should "calculate the amount of correct placed colors" in {
    Code.createTestCode(Array(1,2,3,3))
    Code.eval(Array(1,2,3,4)) should be ((3,0)) 
  }

  it should "calculate the amount of correct colors incorrectly placed" in {
    Code.createTestCode(Array(4,3,2,2))
    Code.eval(Array(1,2,3,4)) should be ((0,3)) 
  }
  
}