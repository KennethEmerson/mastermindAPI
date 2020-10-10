package models
import play.api.libs.json.Json

/**
  * singleton that contains the solution and methods to evaluate guesses
  */
object Code {

  private var solution:Array[Int] = Array(0,0,0,0)    //the actual code
  private var codeLength = solution.length            //length of code
  private var numberOfOptions = 4                     //number of possible colors
  private var evalCounter = 0                         //number of evaluations on code
  this.createNewGame(4,4)

  /** creates a new random solution of pins to guess
    *
    * @param reqAmount: amount of pins to be guessed
    * @param reqOptions: amount of color options every pin can have
    */
  def createNewGame(reqAmount:Int, reqOptions:Int) = {
    codeLength = reqAmount
    numberOfOptions = reqOptions
    evalCounter = 0
    val tempCode = new Array[Int](reqAmount)  
    val r = scala.util.Random
    //create a new array with random numbers
    for(x <- 0 until tempCode.length) tempCode(x) = r.nextInt(numberOfOptions)
    solution = tempCode
  }
  

  /**
    * @return the correct solution as a string
    */
  override def toString(): String = solution.mkString(".")
  
  def getSolution:Array[Int] = solution
  def getEvalCounter:Int = evalCounter
  def getNumberOfOptions:Int = numberOfOptions
  def getCodeLength:Int = codeLength


  /**
    * evaluates a given guess and returns the number of correct guessed pins
    * and the number of wrongly placed pins
    * @param guess: an array that contains the guess that needs to be evaluated
    * @return: tuple with number of correct guessed pins and number of wrong placed pins
    */
  def evaluateGuess(guess:Array[Int]):Option[(Int,Int)] = {
    if(guess.length == solution.length) {
      evalCounter += 1
      val tempSolution = solution.clone()
      var allRight:Int = 0
      var rightColor:Int = 0
      
      //first test if there are correctly placed pins
      for(i <- 0 until tempSolution.length){
        if (tempSolution(i)==guess(i)) {
          allRight +=1
          tempSolution(i) = -1  //if pin is correct it cannot be used for correct colors but wrongly placed check
          guess(i) = -1         //if pin is correct guess pin cannot be used for correct colors but wrongly placed check
        }
      }
      
      //for remaining pins check 
      for(i <- 0 until tempSolution.length){
        for(j <- 0 until guess.length){
          if(tempSolution(i)==guess(j) &&  tempSolution(i)!= -1) {
            rightColor +=1
          tempSolution(i) = -1
            guess(j) = -1
          }
        }  
      }
      Some(allRight,rightColor)
    }
    else None
  }


  /**
    * this function is only used for testing purposes.
    *Allows to inject a solution instead of a random generated one
    * @param testCode : code to use as solution
    */

  def createTestSolution(testSolution:Array[Int]):Unit = {
   solution = testSolution
  }
}