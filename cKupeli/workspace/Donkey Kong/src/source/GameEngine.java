package source;

import java.io.FileNotFoundException;

public class GameEngine {
	private int score;
	private int life = 3;
	private int currentLevel;
	
	/*
	 * During run time map 2D array will change because we do not want user or anyone else to change data inside our level.txt file.
	 * Therefore I did not put any set method for MapData object, even if we want to change it we cannot.
	 */
	private MyObject[][] map = new MyObject[10][10];
	
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

	//File Management Components
	private ScoreData myScoreData;
	private MapData myMapData;
	
	public GameEngine(int level) throws FileNotFoundException{
		myScoreData = new ScoreData();
		myMapData = new MapData(level);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
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

	public MyObject getMapObject(int x, int y) {
		return map[x][y];
	}

	public void setMapObject(MyObject myObject, int x, int y) {
		map[x][y] = myObject;
	}
	
	/*
	 * This method will be initialize after we are done with Player Class which extends MyObject.
	 * DO NOT FORGET TO CHANGE RETURN TYPE TO PLAYER AND CORRECT RETURN STATEMENT AFTER INITIALIZING PLAYER CLASS
	 */
	public MyObject getPlayer(){ //Player getPlayer(){
		return null; //return player;
	}
}
