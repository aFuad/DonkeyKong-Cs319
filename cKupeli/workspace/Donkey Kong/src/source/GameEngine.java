package source;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameEngine {
	private static int totalScore = 0;
	private int score = 0;
	private static int remainingLives = 3;
	private int currentLevel;
	
	private final int JUMP_SPEED_MAX = 20; //Represents maximum limit of vertical speed of jumping
	private int jumpSpeed = JUMP_SPEED_MAX;
	
	private final int GRAVITY_MIN = 0; //Represents minimum limit of gravity
	private final int GRAVITY_MAX = 10; //Represents maximum limit of gravity
	private int gravity = GRAVITY_MIN;
	
	//Check whether or not user pressed W while the Jumpman can jump
	private boolean jump;
	
	/*
	 * During run time map 2D array will change because we do not want user or anyone else to change data inside our level.txt file.
	 * Therefore I did not put any set method for MapData object, even if we want to change it we cannot.
	 */
	private Nonmovable[][] map = new Nonmovable[20][20];
	
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
	 * This ArrayList will help us to check Barrel collisions.
	 * It will store both rolling and falling barrels
	 */
	private ArrayList<Enemy> barrels = new ArrayList<Enemy>();
	
	/*
	 * This ArrayList will help us to check FireElemental collisions.
	 */
	private ArrayList<FireElemental> fireElementals = new ArrayList<FireElemental>();
	
	/*
	 * Player has an important role on our game. Therefore I need an object to reach Player easily.
	 * We have not initialize player yet. Do not forget to do it.
	 */
	private Player player;
	private Oil oil;

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
		for(int y = 0; y < 20; y++){
			for(int x = 0; x < 20; x++){
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
					oil = new Oil(x, y);
					map[x][y] = oil;
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
	
	/*
	 * When players dies and he or she has enough lives to continue reload the map
	 * If statement will be written in GamePanel class
	 */
	public void reLoadMap(int level){
		
	}
	
	/*
	 * Check if the player dies or not.
	 */
	public boolean collusionWithBarrel(){
		return false;
	}
	
	/*
	 * If barrel hits oil barrel. We will remove it from the enemies arrayList then instead add a fire elemental.
	 */
	public void collusionBarrelAndOil(){
		for(int i = 0; i < barrels.size(); i++){
			//This method will be revised later
			if(barrels.get(i).getRectangle().intersects(oil.getRectangle().getMinX() - 5, oil.getRectangle().getMinY(),
				oil.getRectangle().getMaxX() - oil.getRectangle().getMinX(), oil.getRectangle().getMaxY() - oil.getRectangle().getMinY())
				|| barrels.get(i).getRectangle().intersects(oil.getRectangle().getMinX() + 5, oil.getRectangle().getMinY(),
				oil.getRectangle().getMaxX() - oil.getRectangle().getMinX(), oil.getRectangle().getMaxY() - oil.getRectangle().getMinY())){
				createFireElemental((int) barrels.get(i).getRectangle().getMinX(), (int)barrels.get(i).getRectangle().getMinY());
				barrels.remove(i);
			}
		}
	}
	
	public void createBarrel(int x, int y, EnemyType enemyType){
		if(enemyType == EnemyType.ROLLING_BARREL){
			RollingBarrel rollingBarrel = new RollingBarrel(x, y);
			barrels.add(rollingBarrel);
		}
		
		if(enemyType == EnemyType.FALLING_BARREL){
			FallingBarrel fallingBarrel = new FallingBarrel(x,y);
			barrels.add(fallingBarrel);
		}
	}
	
	public void createFireElemental(int x, int y){
		FireElemental fireElemental = new FireElemental(x,y);
		fireElementals.add(fireElemental);
	}
	
	public boolean isJump(){
		return jump;
	}
	
	public void setJump(boolean jump){
		this.jump = jump;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getRemainingLives() {
		return remainingLives;
	}

	public void setRemainingLives(int remainingLives) {
		this.remainingLives = remainingLives;
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
	
	public ArrayList<Enemy> getBarrelList(){
		return barrels;
	}
	
	public ArrayList<FireElemental> getFireElementalList(){
		return fireElementals;
	}
	
	/*
	 * We coded in a way that GamePanel Class reach wPressed(), aPressed(), sPressed(), dPressed() and spacePressed() if and only if game was running.
	 * Therefore we do not need to worry about it.
	 */
	
	public void wPressed(){
		boolean collision = false;
		boolean climb = false;
		for(int i = 0; i < nonmovable.size(); i++){
			//Collision with Platform
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() - 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !nonmovable.get(i).getPassThrough()){
				collision = true;
			}
			
			//Collision with Ladder
			//1st solution (Visually incorrect)
			/*
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				climb = true;
			}
			*/
			//2nd solution
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 15, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 25, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				climb = true;
			}
		}
		
		//Go up Ladder
		if(!collision && climb && jump == false){
			player.goUp();
		}
		
		if(!collision && jumpable()){
			jump = true;
		}
	}
	
	//Check whether or not the Jumpman can jump
	public boolean jumpable(){
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Platform){
				return true;
			}
			
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
					return false;
			}
		}
		return false;
	}
	
	public void aPressed(){
		boolean collision = false;
		int minX = 20 * 50; //We have 20 blocks and each block takes 50 space in x.
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() - 5, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !nonmovable.get(i).getPassThrough()){
				collision = true;
			}
			
			//Collision with borders
			if(nonmovable.get(i).getRectangle().getMinX() < minX){
				minX = (int) nonmovable.get(i).getRectangle().getMinX();
			}
		}
		
		// Collision with borders
		if(getPlayer().getX() < minX + 5){ //5
			collision = true;
		}
		
		if(!collision){
			player.goLeft();
		}
	}
	
	public void sPressed(){
		boolean collision = false;
		boolean goDown = false;
		for(int i = 0; i < nonmovable.size(); i++){
			//Collision
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !nonmovable.get(i).getPassThrough()){
				collision = true;
			}
			
			//Collision with Ladder
			if(((nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())) ||
				(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())))
				&& nonmovable.get(i) instanceof Ladder){
				goDown = true;
			}
		}
		
		if(!collision && goDown){
			player.goDown();
		}
	}
	
	public void dPressed(){
		boolean collision = false;
		int maxX = 0;
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 5, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !nonmovable.get(i).getPassThrough()){
				collision = true;
			}
			
			//Collision with borders
			if(nonmovable.get(i).getRectangle().getMaxX() > maxX){
				maxX = (int) nonmovable.get(i).getRectangle().getMaxX();
			}
		}
		
		// Collision with borders
		if(getPlayer().getX() > maxX - 55){ //945
			collision = true;
		}
		
		if(!collision){
			player.goRight();
		}
	}
	
	public void spacePressed(){
		
	}
	
	public void gravity(){
		boolean collision = false;
		for(int i = 0; i < nonmovable.size(); i++){
			//Collision
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + gravity,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !(nonmovable.get(i) instanceof Ladder)){
				collision = true;
			}
			else if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 15, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 25, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				collision = true;
			}
		}
	
		if(!collision){
			player.setY(player.getY() + gravity);
			player.relocateRectangle(player.getX(), player.getY());
			
			if(gravity < GRAVITY_MAX){
				gravity++;
			}
		}
		else{
			gravity = GRAVITY_MIN;
		}
	}
	
	public void jump(){
		boolean collision = false;
		
		for(int i = 0; i < nonmovable.size(); i++){
			//Collision with Platform
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() - jumpSpeed,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !nonmovable.get(i).getPassThrough()){
				collision = true;
			}
		}
		
		//Collision with top of the window
		if(player.getRectangle().getMinY() - jumpSpeed < 0){
			jumpSpeed = 0;
		}
		
		//Collision with platforms
		if(collision){
			jumpSpeed = 0;
		}
		
		player.setY(player.getY() - jumpSpeed);
		player.relocateRectangle(player.getX(), player.getY());
		
		if(jumpSpeed > 0){
			jumpSpeed--;
		}
		
		for(int i = 0; i < nonmovable.size(); i++){
			//Collision
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 15, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 25, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				jump = false;
			}
			else if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !(nonmovable.get(i) instanceof Ladder)){
				jump = false;
				jumpSpeed = JUMP_SPEED_MAX;
			}
		}
	}
}
