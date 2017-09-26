package postObject;

public class Move {
	
	private String cse_login;
	private String game_id;
	private int x;
	private int y;
	
	public Move(String cse_login, String game_id, int x, int y) {
		super();
		this.cse_login = cse_login;
		this.game_id = game_id;
		this.x = x;
		this.y = y;
	}
	
}
