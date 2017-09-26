package postObject;

public class ShootResult {
	private String game_id;
	private String cse_login;
	private String result;
	private int x;
	private int y;
	private int cost;
	private int score;
	private String game_status;
	private String message;
	
	public String getGame_id() {
		return game_id;
	}
	public String getCse_login() {
		return cse_login;
	}
	public String getResult() {
		return result;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getCost() {
		return cost;
	}
	public int getScore() {
		return score;
	}
	public String getGame_status() {
		return game_status;
	}
	public String getMessage() {
		return message;
	}	
	public int isHit() {
		if (result.equalsIgnoreCase("MISS"))
			return -1;
		if (message == null)
			return 0;
		if (message.indexOf("Destroyer") != -1)
			return 1;
		if (message.indexOf("Submarine") != -1)
			return 2;
		if (message.indexOf("Cruiser") != -1)
			return 3;
		if (message.indexOf("Battleship") != -1)
			return 4;
		if (message.indexOf("Carrier") != -1)
			return 5;		
		return 0;
	}
	public int isEnd() {
		if (game_status.equalsIgnoreCase("won"))
			return 1;
		if (game_status.equalsIgnoreCase("lost"))
			return -1;
		return 0;
	}
	
}
