package source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapData {
	/*
	 * Each block is 50x50, whole frame is 750x750.
	 */
	private String[][] map = new String[15][15]; //Borders of the game map is initialized before
	private Scanner levelScanner;
	private Scanner rowScanner;
	
	public MapData(int level) throws FileNotFoundException{
		levelScanner = new Scanner(new File("src/data/level" + level + ".txt"));
		
		int x = 0;
		int y = 0;

		//2D array filer by using iteration
		while(levelScanner.hasNext()){ //Outer Loop
			String row = levelScanner.nextLine();
			rowScanner = new Scanner(row);
			rowScanner.useDelimiter(" ");
			
			while(rowScanner.hasNext()){ //Inner Loop
				String myObjects = rowScanner.next();
				
				map[x][y] = myObjects;
				
				if(myObjects.equals("S")){
					map[x][y] = "Space"; //S indicates empty space inside matrix
				}
				else if(myObjects.equals("P")){
					map[x][y] = "Platform"; //P indicates platform inside matrix
				}
				else if(myObjects.equals("L")){
					map[x][y] = "Ladder"; //L indicates ladder inside matrix
				}
				else if(myObjects.equals("M")){
					map[x][y] = "Monkey"; //M indicates monkey inside matrix
				}
				else if(myObjects.equals("G")){
					map[x][y] = "Girl"; //G indicates Girl inside matrix
				}
				else if(myObjects.equals("O")){
					map[x][y] = "Oil"; //O indicates oil barrel which spawns fire elementals inside matrix
				}
				else if(myObjects.equals("E")){ //Extra life is a power up
					map[x][y] = "Extra Life"; //EL indicates extra life inside matrix
				}
				else if(myObjects.equals("H")){ //Hammer is a power up
					map[x][y] = "Hammer"; //H indicates hammer inside matrix
				}
				else if(myObjects.equals("J")){ //Jumpman is a power up
					map[x][y] = "Jumpman"; //J indicates Jumpman inside matrix
				}
				
				x++;
			}
			x = 0;
			y++;
		}
		/*
		 * Important Note
		 * Only Nonmoveable objects' location are selected inside this class, Moveable objects will be initilized during runtime.
		 */
	}
	
	public String getMapData(int x, int y){
		return map[x][y];
	}
}
