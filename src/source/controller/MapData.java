package source.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MapData {
	/*
	 * Each block is 50x50, whole frame is 1000x1000.
	 */
	private ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
	private Scanner levelScanner;
	private Scanner rowScanner;
	
	public MapData(int level) throws FileNotFoundException{
		levelScanner = new Scanner(new File("src/data/level" + level + ".txt"));
		
		//2D array filer by using iteration
		while(levelScanner.hasNext()){ //Outer Loop
			String row = levelScanner.nextLine();
			rowScanner = new Scanner(row);
			rowScanner.useDelimiter(" ");
			
			ArrayList<String> rows = new ArrayList<String>();
			while(rowScanner.hasNext()){ //Inner Loop
				String myObjects = rowScanner.next();
				
				if(myObjects.equals("S")){
					rows.add("Space"); //S indicates empty space inside matrix
				}
				else if(myObjects.equals("P")){
					rows.add("Platform"); //P indicates platform inside matrix
				}
				else if(myObjects.equals("L")){
					rows.add("Ladder"); //L indicates ladder inside matrix
				}
				else if(myObjects.equals("M")){
					rows.add("Monkey"); //M indicates monkey inside matrix
				}
				else if(myObjects.equals("G")){
					rows.add("Girl");//G indicates Girl inside matrix
				}
				else if(myObjects.equals("O")){
					rows.add("Oil"); //O indicates oil barrel which spawns fire elementals inside matrix
				}
				else if(myObjects.equals("E")){ //Extra life is a power up
					rows.add("Extra Life"); //EL indicates extra life inside matrix
				}
				else if(myObjects.equals("H")){ //Hammer is a power up
					rows.add("Hammer"); //H indicates hammer inside matrix
				}
				else if(myObjects.equals("C")){ //Coin is an enchanments
					rows.add("Coin"); //C indicates coin inside matrix
				}
				else if(myObjects.equals("B")){ //Barrel Store stands left side or right side of the monkey, depends on the location monkey throw barrels
					rows.add("Barrel Store"); //B indicates barrel store inside matrix
				}
				else if(myObjects.equals("J")){ //Jumpman is a power up
					rows.add("Jumpman"); //J indicates Jumpman inside matrix
				}
				else if(myObjects.equals("A")){ //Spawn Point is a location where enemy barrels will spawns.
					rows.add("Spawn Point"); //A comes from Add, it indicates spawn point inside matrix
				}
				else if(myObjects.equals("F")){ //Finish is the point where Jumpman successfully complete the level
					rows.add("Finish Point"); //F indicates finish point inside matrix
				}
				
			}
			map.add(rows);
		}
		/*
		 * Important Note
		 * Only Nonmoveable objects' location are selected inside this class, Moveable objects will be initilized during runtime.
		 */
	}
	
	public ArrayList<ArrayList<String>> getMapData(){
		return map;
	}
}