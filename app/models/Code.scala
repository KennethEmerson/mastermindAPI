package models
import play.api.libs.json.Json

/**
  * singleton tha contains the solution and methods to evaluate guesses
  */
object Code {
  private var codeLength = 4
  private var numberOfOptions = 4
  private var evalCounter = 0
  private var solution:Array[Int] = Array(1,2,3,4)
  //private var solution:Array[Int] = create(amount,options)
  
  
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
    for(x <- 0 until tempCode.length) tempCode(x) = r.nextInt(numberOfOptions)
    solution = tempCode
  }
  
  /**
    * @return the correct solution as a strin
    */
  override def toString(): String = solution.mkString(" ")
  
  def getSolution:Array[Int] = solution
  def getEvalCounter:Int = evalCounter
  def getNumberOfOptions:Int = numberOfOptions
  def getCodeLength:Int = codeLength

  def stringToArray(guess:String) = {
    guess.split('.').map(_.toInt)
  }

  /**
    * evaluates a given guess and returns the number of correct guessed pins
    * and the number of wrongly placed pins
    * @param guess: an array that contains the guess that needs to be evaluated
    * @return: tuple with number of correct guessed pins and number of wrong placed pins
    */
  def eval(guess:Array[Int]):Option[(Int,Int)] = {
    val temp1 = guess.length
    val temp2 = solution.length
    print(s"guess.length: $temp1")
    print(s"solution.length: $temp2")
    print(guess.mkString(" "))
    if(guess.length == solution.length) {
      evalCounter += 1
      val tempSolution = solution
      var allRight:Int = 0
      var rightColor:Int = 0
      for(i <- 0 until tempSolution.length){
        if (tempSolution(i)==guess(i)) {
          allRight +=1
        tempSolution(i) = -1
          guess(i) = -1
        }
      }
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

  def createTestCode(testCode:Array[Int]):Unit = {
   solution = testCode
  }
}