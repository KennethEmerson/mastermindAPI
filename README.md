# mastermindAPI

A simple API to play mastermind. (And my first attempt in building an API and using docker)

the API works as follows:

GET: /mastermind/newgame/x/y    creates a new game with x pins and y colors. 
                                returns a json showing that the number of guesses is reset and the code length