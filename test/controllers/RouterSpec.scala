package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._

class RouterSpec extends PlaySpec with GuiceOneAppPerTest {
  "A GET request" should {
    "render the index page from the router" in {
      val request = FakeRequest(GET, "/mastermind/")
      val home = route(app, request).get
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Mastermind")
    }

    "render the new game page from the router" in {
      val request = FakeRequest(GET, "/mastermind/newgame/4/4")
      val reply = route(app, request).get
      status(reply) mustBe OK
      contentAsString(reply) must include ("numberOfGuesses")
      contentAsString(reply) must include ("codeLength")
    }

    "render the evaluation page from the router" in {
      val request = FakeRequest(GET, "/mastermind/evaluation/1.2.3.4")
      val reply = route(app, request).get
      status(reply) mustBe OK
      contentAsString(reply) must include ("numberOfGuesses")
      contentAsString(reply) must include ("correct")
      contentAsString(reply) must include ("wrongPlace")
    }

    "render the solution page from the router" in {
      val request = FakeRequest(GET, "/mastermind/solution")
      val reply = route(app, request).get
      status(reply) mustBe OK
      contentAsString(reply) must include ("numberOfGuesses")
      contentAsString(reply) must include ("solution")
    }
  }
}