package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Placement {
	
	private int a[][] = new int[10][10];
	int health[] = {2, 3, 3, 4, 5};
	
	public boolean isWin() {
		int sum = 0;
		for(int i=0; i<5; i++) 
			sum += health[i];
		return sum == 0;
	}
	
	private boolean place (int x, int y, int len, boolean ver, int ship) {
		if (ver) {			
			for(int i=0; i<len; i++)
				if (x+i >= 10 || a[x+i][y] != 0) {
					System.out.println("Invalid position");
					return false;
				}
			for(int i=0; i<len; i++) 
				a[x+i][y] = ship;
		} else {
			for(int i=0; i<len; i++) 
				if (y+i >= 10 || a[x][y+i] != 0) {
					//System.out.println("Invalid position");
					return false;
				}
			for(int i=0; i<len; i++)
				a[x][y+i] = ship;
		}
		return true;
	}
	
	public Placement() {
		int seed;		
		Scanner sc;
		sc = new Scanner(System.in);
		seed = sc.nextInt();
		Random rd = new Random(seed);
		
		try {
			sc = new Scanner(new File("data/input.txt"));		
			for(int i=1; i<=5; i++) {
				int x, y, len, ver;		
				do {			
					System.out.println("input x, y, size, ver: ");
					x = sc.nextInt();
					y = sc.nextInt();
					len = sc.nextInt();
					ver = sc.nextInt();
				} while (!place(x, y, len, ver == 1, i));
				System.out.println(i);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Placement(int seed) {	
		Random rd = new Random(seed);
				
		for(int i=1; i<=5; i++) {
			int x, y, len, ver;		
			do {			
				System.out.println("input x, y, size, ver: ");				
				x = rd.nextInt(10);
				y = rd.nextInt(10);
				len = health[i-1];
				ver = rd.nextInt(100) % 2;
			} while (!place(x, y, len, ver == 1, i));
			System.out.println(i);
		}
	}
	
	public int shoot(int x, int y) {
		int stt = 0;		
		if (x<0 || y<0 || x>9 || y>9)
			throw new RuntimeException("Out of bound " + x + " " + y);
		if (a[x][y] == -1)
			throw new RuntimeException("Duplicate shoot " + x + " " + y);		
		if (a[x][y] == 0) {
			a[x][y] = -1;
			return -1;
		}
		--health[a[x][y]-1];
		if (health[a[x][y]-1] == 0) {
			stt = a[x][y];
			a[x][y] = -1;			
		}
		a[x][y] = -1;
		return stt;
	}
	
	public int[][] getBoard() {
		return a; 
	}
	
	@Override
	public String toString() {
		String res = "";
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++)
				res = res + String.format("%10d", a[i][j]);
			res = res + "\n";
		}
		for(int i=0; i<5; i++) 
			res = res + Integer.toString(this.health[i]) + " ";
		return res;
	}

}
