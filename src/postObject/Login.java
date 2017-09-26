package postObject;

public class Login {
	private String cse_login;
	private String game_id;
	
	public Login(String cse_login, String game_id) {
		this.cse_login = cse_login;
		this.game_id = game_id;
	}

	public String getCse_login() {
		return cse_login;
	}

	public String getGame_id() {
		return game_id;
	}
	
}
