# mastermind API

A simple API to play mastermind written in Scala using the Play framework.
(And my first attempt in building an API and using docker)


A dockerfile to build a docker image and unittests using scalatest and Scoverage are included

* Scala version: 2.13.1
* SBT version: 1.3.8
* PLay version: 2.8.0


##### the API works as follows:

 <strong>GET: /mastermind/newgame/x/y</strong>   

creates a new game with x pins and y colors. returns a json string: {"numberOfGuesses":0,"codeLength":5}
which shows that the number of guesses is reset and the code length of the newly generated code

<strong>GET: /mastermind/evaluation/x.x.x.x</strong>

In the API, every color is represented by a integer color code
To evaluate the guess, place the code after evaluation/ where every 'x' represents a color code for the pin in tha position.

A JSON string will be returned containing: {"numberOfGuesses":1,"correct":0,"wrongPlace":0} where
* 'correct' is the number of correctly placed pins.
* 'wrongPLace' is the number of pins which are wronlgy placed but have the right color.


<strong>GET: /mastermind/solution</strong>

returns a JSON string:{"numberOfGuesses":0,"solution":[0,1,0,1]} containing the number of guesses and the actual solution.
