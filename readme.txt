Battleship game.
1. Game rule

2. Idea
With a status of the board, calculate the probability of hit chance (the cell belongs to one of the ships) when you shoot in the cell. Then we choose to shoot one with highest probability.
However, the calculation of actual probability is too complicated, so we define new way of representing our chance of successful hit.

3. New function
In a state of the board, each cell has a score defined as:
	-1 if it is shot and the result is missed.
	-1 if it belongs to a destroyed ship.
	X otherwise.

How the value of x is calculated.
With a particular ship and position: calculate the score s = 35^k, where k is number of shot cell with hit result and not belongs to a destroyed ship. The score of every cell that the ship lies on is increased by s. s is 0 if we can’t place the ship into that position.
The score of cell (x,y) X is the total score s you have for every way you can place a ship lying over that cell (x,y)

4. Why the score is the power of 25?
In practice, we are more interested in those ship’s position that covers one or more “hit” shoot than those we have not tried yet, so every “hit” cell covered, more score should be added. That is why I think of the geometric sequence.
After running and analyzing all the base from 2 to 35 with more than 1000 games, I found that 25 had lowest percentages of losing. So I go with 25.

5. Compile and run
This project is written in Java using Eclipse IDE.
The jar file consists of all required files for execution.
Run the jar file with command java -jar Battleship.jar.
You will input your cse login and number of games you want to play, then the program automatically interacts with the sever and plays the games.
You can watch the process and result in the console window. More detailed report including game ID, use login, and list of moves and their verdicts for each game is included in logs\logXML.log.
An archive with the source code and all required external library is also provided.

6. Analyze
The complexity of is proportional to the number of gameplays, size of the board (with is 10x10), and the number of maximun shoots (60).
In real time, average time when running on cse sever is 4 second a game.
This program has the win rate of about 93% based on the result of more than 1000 different boards.
Some won game with my cse login (kphan)
7221f975
5e8d0774
68745f84
d6111981
