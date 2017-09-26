package main;

public class Board {
	
	//represent the direction: 0..3 = {North, East, South, West}
	private static int[] ma1 = {-1,0,1,0};
	private static int[] ma2 = {0,1,0,-1};
	
	public static int[] shipSize = {2, 3, 3, 4, 5};
	public static int maxMove = 60;

	private String cse_login;
	private String game_id;
	private int score = 0;
	private int[][] stt = new int[10][10]; //0: not shot, 1: hit, -1: no more ship there.
	
	//Bit represents ships that is not shot down: Minesweeper, Submarine, Cruiser, Battleship, Carrier. 
	private int ship = 1 + 2 + 4 + 8 + 16;
	
	public Board(String cse_login, String game_id) {
		this.cse_login = cse_login;
		this.game_id = game_id;
	}

	public String getCse_login() {
		return cse_login;
	}

	public String getGame_id() {
		return game_id;
	}

	public int getScore() {
		return score;
	}

	public int[][] getStatus() {
		return stt;
	}
	
	public void shoot(int x, int y, int hit) {
		if (hit == -1) 
			stt[x][y] = -1;
		else {
			stt[x][y] = 1;
			if (hit > 0) 
				placeShip(x, y, hit-1);
		}
		score++;
	}
	
	private void check(int x, int y, int len, int[][] a) {
		int cnt = 1;
		boolean ok = true;
		for(int i=0; i<len; i++)
			if (x+i >=10 || stt[x+i][y] == -1)
				ok = false;
			else if (stt[x+i][y] == 1) 
				cnt *= 35;		
		if (ok) 
			for(int i=0; i<len;i++) 
				a[x+i][y] += 1 + cnt;
		cnt = 1;
		ok = true;
		for(int i=0; i<len; i++)
			if (y+i >=10 || stt[x][y+i] == -1)
				ok = false;
			else if (stt[x][y+i] == 1) 
				cnt *= 35;		
		if (ok) 
			for(int i=0; i<len;i++) 
				a[x][y+i] += 1 + cnt;
	}
	
	//Calculate the probability of the board
	public int[][] calProbability() {
		int[][] a = new int[10][10];
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++) 
				if (stt[i][j] != -1) {
					if (ship % 2 == 1) check(i, j, 2, a);
					if ((ship >> 1) % 2 ==1) check(i, j, 3, a);
					if ((ship >> 2) % 2 ==1) check(i, j, 3, a);
					if ((ship >> 3) % 2 ==1) check(i, j, 4, a);
					if ((ship >> 4) % 2 ==1) check(i, j, 5, a);
				}
		for(int i=0; i<10; i++) 
			for(int j=0; j<10; j++)
				if (stt[i][j] != 0)
					a[i][j] = -1000;
		return a;
	}
	
	//Check if a ship can be place at (x,y) with direction = len
	private int checkShip(int x, int y, int dir, int len) {
		int cnt = 0;
		for(int i=0; i<len; i++) 
			if (x+ma1[dir]*i >=0 && y+ma2[dir]*i >=0 && x+ma1[dir]*i < 10 && y+ma2[dir]*i < 10)
				if (stt[x+ma1[dir]*i][y+ma2[dir]*i] == 1)
					++cnt;
				else if (stt[x+ma1[dir]*i][y+ma2[dir]*i] == -1)
					return -1;
		return cnt;
	}
	
	//Place a ship when hit
	public void placeShip(int x, int y, int ship) {
		for(int i=0; i<4; i++) 
			if (checkShip(x, y, i, shipSize[ship]) == shipSize[ship]) {
				for(int j=0; j<shipSize[ship]; j++)
					stt[x+ma1[i]*j][y+ma2[i]*j] = -1;
				this.ship -= (1 >> ship);
				return;
			}
	}
	
	public int shootAtRow() {
		int a[][] = this.calProbability();
		int x = 0;
		int y = 0;
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				if (a[x][y] < a[i][j])  {
					x = i;
					y = j;
				}
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				if ((i+j)%2==1 && a[x][y] < a[i][j])  {
					x = i;
					y = j;
				}
		return x;
	}
	
	public int shootAtColumn() {
		int a[][] = this.calProbability();
		int x = 0;
		int y = 0;
		for(int i=0; i<10; i++)
			for(int j=0; j<10; j++)
				if (a[x][y] < a[i][j])  {
					x = i;
					y = j;
				}
		return y;
	}
	
	public boolean isGameOver() {
		return score == maxMove;
	}	
	
	@Override
	public String toString() {
		String res = "Board\n";
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++)
				res = res + String.format("%10d", this.stt[i][j]);
			res = res + "\n";
		}
		return res;
	}
}
