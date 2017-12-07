<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mapper {
	
	public static final int MAP_WIDTH = 30;
	public static final int MAP_HEIGHT = 20;
	
	public static final int TILE_WIDTH = 50;
	public static final int TILE_HEIGHT = 50;
	
	private static final int TILE_ID = 1;
	private static final int PLAYER_ID = 2;
	private static final int SLIME_ID = 3;
	private static final int END_ID = 4;
	private static final int GHOST_ID = 5;
	private static final int PENGU_ID = 6;
	private static final int BOSS_ID = 7;
	
	private PlayerSprite player;
	private EndTile endTile;
	
	int [][]intMap;
	
	/*
	 * Constructs the initial map with a text file filled with integers 
	 * according to final length and height parameters.
	 * @param map File to be read.
	 * @return Mapper object.
	 */
	
	public Mapper(File map) {
		intMap = new int[MAP_HEIGHT][MAP_WIDTH];
		try {
			Scanner s = new Scanner (map);
			int row = 0;
			while(s.hasNextLine()) {
				String line = s.nextLine();
				line.trim();
				for(int col = 0; col < line.length(); col++) {
					intMap[row][col] = Integer.parseInt (line.substring(col, col+1));
				}
				row++;
			}
			s.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Map not found.");
		}

	}
	
	/*private void printIntMap() {
		for(int i = 0; i < MAP_WIDTH; i++) {
			for (int j = 0; j < MAP_HEIGHT; j++) {
				System.out.print(intMap[i][j] + " ");
			}
			System.out.print("\n");
		}
	}*/
	
	/*
	 * Creates a 2 dimensional array containing all starting sprites.
	 * @param tile type to build array with
	 * @return 2 dimensional array with sprites.
	 */
	public Sprite[][] initialMap(Tile.Terrain t){
		
		Sprite[][] initialMap = new Sprite[MAP_WIDTH][MAP_HEIGHT];
		for(int row = 0; row < MAP_WIDTH; row++) {
			for (int col = 0; col < MAP_HEIGHT; col++) {
				if (intMap[col][row] == TILE_ID) {
					initialMap[row][col] = new Tile(t, row, col);
				}
				else if (intMap[col][row] == PLAYER_ID) {
					player = new PlayerSprite (row, col);
					initialMap[row][col] = player;
				}
				else if (intMap[col][row] == SLIME_ID) {
					initialMap[row][col] = new SlimeSprite (row, col);
				}
				else if (intMap[col][row] == END_ID) {
					endTile = new EndTile (row, col);
					initialMap[row][col] = endTile;
				}
				else if (intMap[col][row] == GHOST_ID) {
					initialMap[row][col] = new GhostSprite(row,col);
				}
				else if (intMap[col][row] == PENGU_ID) {
					initialMap[row][col] = new PenguSprite(row,col);
				}
				else if (intMap[col][row] == BOSS_ID) {
					initialMap[row][col] = new BossSprite(row,col);
				}
			}
		}
		return initialMap;
	}
	
	public PlayerSprite getPlayer() {
		return player;
	}
	
	public EndTile getEndTile() {
		return endTile;
	}

}
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.scene.image.Image;

public class Mapper {
	
	public static final int MAP_WIDTH = 25;
	public static final int MAP_HEIGHT = 15;
	
	public static final int TILE_WIDTH = 50;
	public static final int TILE_HEIGHT = 50;
	
	private static final int TILE_ID = 1;
	
	int [][]intMap;
	
	/*
	 * Constructs the initial map with a text file filled with integers 
	 * according to final length and height parameters.
	 * @param map File to be read.
	 * @return Mapper object.
	 */
	
	public Mapper(File map) {
		intMap = new int[MAP_HEIGHT][MAP_WIDTH];
		try {
			Scanner s = new Scanner (map);
			int row = 0;
			while(s.hasNextLine()) {
				String line = s.nextLine();
				line.trim();
				for(int col = 0; col < line.length(); col++) {
					intMap[row][col] = Integer.parseInt (line.substring(col, col+1));
				}
				row++;
			}
			s.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Map not found.");
		}

	}
	
	private void printIntMap() {
		for(int i = 0; i < MAP_WIDTH; i++) {
			for (int j = 0; j < MAP_HEIGHT; j++) {
				System.out.print(intMap[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	private void arrayReverse(int[] arr) {
		for (int i = 0; i < arr.length/2; i++) {
			int temp = arr[i];
			arr[i] = arr[arr.length-1-i];
			arr[arr.length-1-i] = temp;
		}
	}
	
	/*
	 * Creates a 2 dimensional array containing all starting sprites.
	 * @param tile type to build array with
	 * @return 2 dimensional array with sprites.
	 */
	public Sprite[][] initialMap(Tile.Terrain t){
		
		Sprite[][] initialMap = new Sprite[MAP_WIDTH][MAP_HEIGHT];
		for(int row = 0; row < MAP_WIDTH; row++) {
			for (int col = 0; col < MAP_HEIGHT; col++) {
				if (intMap[col][row] == TILE_ID) {
					initialMap[row][col] = new Tile(t, row * TILE_WIDTH, col * TILE_HEIGHT);
				}
			}
		}
		return initialMap;
	}

}
>>>>>>> be47220a2d0a237f3cc7a96c748cbc1a94741040
