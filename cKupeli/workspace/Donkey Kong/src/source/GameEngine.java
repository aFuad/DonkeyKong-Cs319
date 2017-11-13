package source;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameEngine {
	private int score;
	private int numberOfLives = 3;
	private int currentLevel;
	
	/*
	 * During run time map 2D array will change because we do not want user or anyone else to change data inside our level.txt file.
	 * Therefore I did not put any set method for MapData object, even if we want to change it we cannot.
	 */
	private Nonmovable[][] map = new Nonmovable[15][15];
	
	 /* 
	  * boolean gameOver
	  * Purpose of gameOver is to identify the difference between pause() and stop() in GamePanel.
	  * Therefore we can stop rendering the screen and return back to main menu
	  * False means game is still running
	  * True means game is over
	  */
	private boolean gameOver = false;
	
	/*
	 * boolean movement
	 * Purpose of movement is to identify the difference between pause() and stop() in GamePanel.
	 * Therefore we stop rendering; however unlike gameOver, we do not return back to main menu
	 * True means game is not paused
	 * False means game is paused
	 */
	private boolean movement = true;
	
	/*
	 * We store map objects inside an ArrayList as well in order to use them to implement collision.
	 * I was planning to use ArrayList for map as well, but I end up facing some technicality inside GamePanel.
	 */
	private ArrayList<Nonmovable> nonmovable = new ArrayList<Nonmovable>();
	
	/*
	 * Player has an important role on our game. Therefore I need an object to reach Player easily.
	 * We have not initialize player yet. Do not forget to do it.
	 */
	private Player player;

	//File Management Components
	private ScoreData myScoreData;
	private MapData myMapData;
	
	public GameEngine(int level) throws FileNotFoundException{
		myScoreData = new ScoreData();
		myMapData = new MapData(level);
		loadMap();
	}
	
	//Load the map as objects
	private void loadMap(){
		for(int y = 0; y < 15; y++){
			for(int x = 0; x < 15; x++){
				if(myMapData.getMapData(x, y) == "Space"){
					map[x][y] = null;
				}
				else if(myMapData.getMapData(x, y) == "Platform"){
					map[x][y] = new Platform(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "Ladder"){
					map[x][y] = new Ladder(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "Monkey"){
					map[x][y] = new Monkey(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "Girl"){
					map[x][y] = new Girl(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "Oil"){
					map[x][y] = new Oil(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "ExtraLife"){
					map[x][y] = new ExtraLife(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "Hammer"){
					map[x][y] = new Hammer(x, y);
					nonmovable.add(getMapObject(x, y));
				}
				else if(myMapData.getMapData(x, y) == "Jumpman"){
					player = new Player(x, y);
				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNumberOfLives() {
		return numberOfLives;
	}

	public void setNumberOfLives(int numberOfLives) {
		this.numberOfLives = numberOfLives;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
	
	public void updateHighScore(int score){
		if(score > myScoreData.getScore()){
			myScoreData.setScore(score);
		}
	}

	public boolean isMovement() {
		return movement;
	}

	public void setMovement(boolean movement) {
		this.movement = movement;
	}

	public Nonmovable getMapObject(int x, int y) {
		return map[x][y];
	}
	
	public Player getPlayer(){
		return player;
	}
	
	/*
	 * We coded in a way that GamePanel Class reach wPressed(), aPressed(), sPressed(), dPressed() and spacePressed() if and only if game was running.
	 * Therefore we do not need to worry about it.
	 */
	public void wPressed(){
		boolean collusion = false;
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() - 5,
					player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
					&& !nonmovable.get(i).getPassThrough()){
				collusion = true;
			}
		}
		
		if(!collusion){
			player.goUp();
		}
	}
	
	public void aPressed(){
		boolean collusion = false;
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() - 5, player.getRectangle().getMinY(),
					player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
					&& !nonmovable.get(i).getPassThrough()){
				collusion = true;
			}
		}
		
		if(!collusion){
			player.goLeft();
		}
	}
	
	public void sPressed(){
		boolean collusion = false;
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + 5,
					player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
					&& !nonmovable.get(i).getPassThrough()){
				collusion = true;
			}
		}
		
		if(!collusion){
			player.goDown();
		}
	}
	
	public void dPressed(){
		boolean collusion = false;
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 5, player.getRectangle().getMinY(),
					player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
					&& !nonmovable.get(i).getPassThrough()){
				collusion = true;
			}
		}
		
		if(!collusion){
			player.goRight();
		}
	}
	
	public void spacePressed(){
		/*
		 * Player can use space button if and only if he/she has hammer buff.
		 * Therefore we need an if statement to decide player can use strike action
		 * if(getPlayer().canStrike()){
		 *  strike();
		 * }
		 */
	}
}
