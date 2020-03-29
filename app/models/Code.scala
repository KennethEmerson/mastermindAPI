package models
import play.api.libs.json.Json

/**
  * singleton tha contains the solution and methods to evaluate guesses
  */
object Code {
  private var amount = 4
  private var options = 4
  private var evalCounter = 0
  private var solution:Array[Int] = Array(1,2,3,4)
  //private var solution:Array[Int] = create(amount,options)
  
  
  /** creates a new random solution of pins to guess 
    *
    * @param reqAmount: amount of pins to be guessed
    * @param reqOptions: amount of color options every pin can have
    */
  def create(reqAmount:Int, reqOptions:Int) = {
    amount = reqAmount
    options = reqOptions
    evalCounter = 0
    val solution:Array[Int] = new Array[Int](amount)
    val r = scala.util.Random
    for(x <- 0 until solution.length) solution(x) = r.nextInt(options)
   solution
  }
  
  /**
    * @return the correct solution as a strin
    */
  override def toString(): String = solution.mkString(" ")
  
  def getSolution:Array[Int] = solution
  def getEvalCounter:Int = evalCounter

  implicit def stringToArray(guess:String) = {
    guess.split(",").map(_.toInt)
  }

  /**
    * evaluates a given guess and returns the number of correct guessed pins
    * and the number of wrongly placed pins
    * @param guess: an array that contains the guess that needs to be evaluated
    * @return: tuple with number of correct guessed pins and number of wrong placed pins
    */
  def eval(guess:Array[Int]):(Int,Int) = {
    require(guess.length == solution.length)
    evalCounter += 1
    var allRight:Int = 0
    var rightColor:Int = 0
    for(i <- 0 until solution.length){
      if (solution(i)==guess(i)) {
        allRight +=1
       solution(i) = -1
        guess(i) = -1
      }
    }
    for(i <- 0 until solution.length){
      for(j <- 0 until guess.length){
        if(solution(i)==guess(j) &&  solution(i)!= -1) {
          rightColor +=1
         solution(i) = -1
          guess(j) = -1
        }
      }  
    }
    (allRight,rightColor)
  }

  def createTestCode(testCode:Array[Int]):Unit = {
   solution = testCode
  }
}