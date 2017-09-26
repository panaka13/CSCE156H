package main;
import java.io.InputStream;
import java.util.Scanner;

import com.google.gson.Gson;

import postObject.Login;
import postObject.Move;
import postObject.ShootResult;

public class Driver {
	
	Board board;
	Gson gson = new Gson();
	
	public Driver() {
		
		Scanner sc = new Scanner(System.in);
		
		Placement game = new Placement(12321);
		System.out.println(game);
		Board board = new Board("ss", "sss");
		
		while (!board.isGameOver()) {
			int x = board.shootAtRow();
			int y = board.shootAtColumn();
			System.out.println("Shoot at " + x + " " + y);
			System.out.println("Hit or not?");
			int kq = game.shoot(x, y);		
			System.out.println("Shoot at " + x + " " + y + " " + kq);
			board.shoot(x, y, kq);
//			System.out.println(game);
			if (game.isWin()) {
				System.out.println("You win!!! Used " + board.getScore() + " move.");
				break;
			}
		}
		
		System.out.println(board);
	}
	
	public Driver(String cse_login) {
		String s = Test.testPost("http://csce.unl.edu:8080/Battleship/NewGame", "cse_login", cse_login);
		System.out.println(s);
		Login login = gson.fromJson(s, Login.class);
		board = new Board(login.getCse_login(), login.getGame_id());
		boolean gameOver = false;
		while (!gameOver) {
			int x = board.shootAtRow();
			int y = board.shootAtColumn();
			Move move = new Move(login.getCse_login(), login.getGame_id(), x, y);
			s = Test.testPost("http://csce.unl.edu:8080/Battleship/Move", "move", gson.toJson(move));
			logFile.LogFile.importToLog(s);
			ShootResult result = gson.fromJson(s, ShootResult.class);
			switch (result.isEnd()) {
			case 1:
				System.out.println("I won the game ID " + login.getGame_id());
				logFile.LogFile.importToLog("I won the game ID " + login.getGame_id());
				gameOver = true;
				break;
			case -1:
				System.out.println("I lost the game ID " + login.getGame_id());
				logFile.LogFile.importToLog("I lost the game ID " + login.getGame_id());
				gameOver = true;
				break;
			}					
			board.shoot(x, y, result.isHit());
			if (board.isGameOver())
				break;
		}
	}
	
	static public void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your cse login?");
		String login = sc.nextLine();
		System.out.println("How many game you want me to play? ");
		int limit = sc.nextInt();
		for(int i=0; i<limit; i++) {
			System.out.println("Game #" + (i+1));
			new Driver(login);
		}			
	}

}
