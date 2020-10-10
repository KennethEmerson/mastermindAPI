package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class ApiControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "ApiController" should {

    "render the index page" in {
      val controller = inject[ApiController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Mastermind")
    }

    "render the json from the new game" in {
      val controller = inject[ApiController]
      val reply = controller.newGame(4,4).apply(FakeRequest(GET, "newgame/4/4"))
      contentAsString(reply) must include ("numberOfGuesses")
      contentAsString(reply) must include ("codeLength")
    }

    "render the json from the evaluation" in {
      val controller = inject[ApiController]
      val reply = controller.evalGuess("1.2.3.4").apply(FakeRequest())
      contentAsString(reply) must include ("numberOfGuesses")
      contentAsString(reply) must include ("correct")
      contentAsString(reply) must include ("wrongPlace")
    }

    "render the json from the solution" in {
      val controller = inject[ApiController]
      val reply = controller.getSolution().apply(FakeRequest())
      contentAsString(reply) must include ("numberOfGuesses")
      contentAsString(reply) must include ("solution")
    }
    
    "render the error page" in {
      val controller = inject[ApiController]
      val reply = controller.invalidRequest("bad request").apply(FakeRequest())
      contentAsString(reply) must include ("Invalid Request")
    }

  }
}