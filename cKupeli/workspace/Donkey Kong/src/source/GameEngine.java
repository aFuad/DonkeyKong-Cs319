package source;

import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameEngine {
	private int score;
	private int remainingLives = 3;
	private int level;
	
	/*
	 * When the Jumpman jumps, it should take enough time to a barrel move under him.
	 * 
	 * S: Space
	 * J: Jumpman
	 * B: Barrel
	 * S S S
	 * S S S
	 * S J B
	 * 
	 * As it can be seen from the matrix, barrel must move at least 2 space
	 * We do not want Jumpman to hit other platform, distance between platforms is 150 pixel
	 * 
	 * V_Barrel(0) = 5 (Constant)
	 * V_Jumpman(0) = 17 (Acceleration = -1)
	 * 
	 * d = (V(0)*t)
	 * h_Max = (V(0)^2) / (2*(A))
	 * h = (V(0)*t) + ((1/2)*(A)*(t^2))
	 * 
	 * 100 = (V_Barrel(0) = 5)*t
	 * t = 20
	 * 
	 * 150 = ((V_Jumpman(0)) * 20) + ((1/2)*(-1)*(20^2 = 200))
	 * 150 = ((V_Jumpman(0)) * 20) - 200
	 * 350 = (V_Jumpman(0)) * 20
	 * V_Jumpman(0) 17.5 which is around 17
	 */
	private final int JUMP_SPEED_MAX = 17; //Represents maximum limit of vertical speed of jumping
	private int jumpSpeed = JUMP_SPEED_MAX;
	
	private final int GRAVITY_MIN = 0; //Represents minimum limit of gravity
	private final int GRAVITY_MAX = 5; //Represents maximum limit of gravity
	private int gravity = GRAVITY_MIN;
	
	//Spawn a barrel every 10 seconds
	private final int BARREL_SPAWN_TIMER = 1000 * 10; //1000 means 1 second and 10 is the multiplier
	
	//Check whether or not user pressed W while the Jumpman can jump
	private boolean jump;
	
	/*
	 * During run time map 2D array will change because we do not want user or anyone else to change data inside our level.txt file.
	 * Therefore I did not put any set method for MapData object, even if we want to change it we cannot.
	 */
	private ArrayList<ArrayList<Nonmovable>> map = new ArrayList<ArrayList<Nonmovable>>();
	
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
	private ArrayList<Barrel> barrels = new ArrayList<Barrel>();
	
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
		this.level = level;
		score = 0;
		loadMap();
	}
	
	//Barrel spawn point x and y values which will be set inside loadMap
	private int barrelSpawnX;
	private int barrelSpawnY;
	
	//Load the map as objects
	private void loadMap(){
		for(int y = 0; y < myMapData.getMapData().size(); y++){
			ArrayList<String> innerListString = myMapData.getMapData().get(y);
			ArrayList<Nonmovable> innerListNonmovable = new ArrayList<Nonmovable>();
			for(int x = 0; x < innerListString.size(); x++){
				if(innerListString.get(x) == "Space"){
					innerListNonmovable.add(null);
				}
				else if(innerListString.get(x) == "Platform"){
					Platform platform = new Platform(x, y);
					innerListNonmovable.add(platform);
					nonmovable.add(platform);
				}
				else if(innerListString.get(x) == "Ladder"){
					Ladder ladder = new Ladder(x, y);
					innerListNonmovable.add(ladder);
					nonmovable.add(ladder);
				}
				else if(innerListString.get(x) == "Monkey"){
					Monkey monkey = new Monkey(x, y);
					innerListNonmovable.add(monkey);
					nonmovable.add(monkey);
				}
				else if(innerListString.get(x) == "Girl"){
					Girl girl = new Girl(x, y);
					innerListNonmovable.add(girl);
					nonmovable.add(girl);
				}
				else if(innerListString.get(x) == "Oil"){
					oil = new Oil(x, y);
					innerListNonmovable.add(oil);
					nonmovable.add(oil);
				}
				else if(innerListString.get(x) == "Extra Life"){
					ExtraLife extraLife = new ExtraLife(x, y);
					innerListNonmovable.add(extraLife);
					nonmovable.add(extraLife);
				}
				else if(innerListString.get(x) == "Hammer"){
					Hammer hammer = new Hammer(x, y);
					innerListNonmovable.add(hammer);
					nonmovable.add(hammer);
				}
				else if(innerListString.get(x) == "Barrel Store"){
					BarrelStore barrelStore = new BarrelStore(x, y);
					innerListNonmovable.add(barrelStore);
					nonmovable.add(barrelStore);
				}
				else if(innerListString.get(x).equals("Jumpman")){
					player = new Player(x, y);
					innerListNonmovable.add(null);
				}
				else if(innerListString.get(x).equals("Spawn Point")){
					barrelSpawnX = x;
					barrelSpawnY = y;
				}
			}
			map.add(innerListNonmovable);
		}
	}
	
	//When players dies and he or she has enough lives to continue reload the map
	public void reloadMap(){
		//Set the number of barrels and fire elementals to zero
		barrels.clear();
		fireElementals.clear();
		
		/*
		 * Decrement the number of remainingLives by one in order to decide game is over or not.
		 * remainingLives = 0 means game is over
		 */
		remainingLives--;
		
		//Lit is always false at the start of the game
		oil.setLit(false);
		
		//Set the score 0
		score = 0;
		
		//Return player's location back to its original
		for(int y = 0; y < myMapData.getMapData().size(); y++){
			ArrayList<String> innerListString = myMapData.getMapData().get(y);
			for(int x = 0; x < innerListString.size(); x++){
				if(innerListString.get(x).equals("Jumpman")){
					player = new Player(x, y);
				}
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
	
	public ArrayList<ArrayList<Nonmovable>> getMapObjects(){
		return map;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public ArrayList<Barrel> getBarrelList(){
		return barrels;
	}
	
	public ArrayList<FireElemental> getFireElementalList(){
		return fireElementals;
	}
	
	/*
	 * We coded in a way that GamePanel Class reach wPressed(), aPressed(), sPressed(), dPressed() and spacePressed() if and only if game was running.
	 * Therefore we do not need to worry about it.
	 */
	
	public int getBarrelSpawnX() {
		return barrelSpawnX;
	}

	public void setBarrelSpawnX(int barrelSpawnX) {
		this.barrelSpawnX = barrelSpawnX;
	}

	public int getBarrelSpawnY() {
		return barrelSpawnY;
	}

	public void setBarrelSpawnY(int barrelSpawnY) {
		this.barrelSpawnY = barrelSpawnY;
	}
	
	public EnemyType rollEnemyType(){
		/*
		 * Getting a number lower than 0.25 and greater than 0.75 is harder than getting greater or lower than 0.5 due to implementation of random method.
		 * We do not want a total 50/50 scenario for selecting an barrel type.
		 */
		if(Math.random() > 0.75 || Math.random() < 0.25){
			return EnemyType.FALLING_BARREL;
		}
		return EnemyType.ROLLING_BARREL;
	}
	
	//Create movement for single barrel which is taken as a parameter
	public void moveBarrel(Barrel barrel){
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(barrel.getRectangle().getMinX(), barrel.getRectangle().getMinY() + 5,
				barrel.getRectangle().getMaxX() - barrel.getRectangle().getMinX(), barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Platform){
				barrel.setFalling(false);
				break;
			}
			else if(nonmovable.get(i).getRectangle().intersects(barrel.getRectangle().getMinX(), barrel.getRectangle().getMinY() + 5,
				barrel.getRectangle().getMaxX() - barrel.getRectangle().getMinX(), barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				if(fallBarrel(barrel)){
					barrel.setFalling(true);
				}
			}
		}
		
		if(!barrel.isFalling()){
			boolean falling = true;
			
			for(int i = 0; i < nonmovable.size(); i++){
				if(nonmovable.get(i).getRectangle().intersects(barrel.getRectangle().getMinX(), barrel.getRectangle().getMinY() + 5,
				barrel.getRectangle().getMaxX() - barrel.getRectangle().getMinX(), barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY())){
					falling = false;
				}
			}
			
			if(falling){
				barrel.setFalling(true);
			}
		}
		
		
		if(barrel.isFalling()){
			barrel.fallDown();
		}
		else{
			if(barrel.getRectangle().getMaxX() == 1000){ //Boundary points
				barrel.setDirection(Direction.LEFT);
			}
			else if(barrel.getRectangle().getMinX() == 0){ //Boundary points
				barrel.setDirection(Direction.RIGHT);
			}
			
			if(barrel.getDirection() == Direction.RIGHT){
				barrel.goRight();
			}
			else if(barrel.getDirection() == Direction.LEFT){
				barrel.goLeft();
			}
		}
		
		collisionBarrelAndPlayer(barrel);
		collisionBarrelAndOil(barrel);
	}
	
	/*
	 * moveFireElemental was originally written in assembly language in Donkey Kong 1981.
	 * Fortunately a group of people on Internet analyzed it in following article.
	 * http://donkeykongblog.blogspot.com.tr/2011/12/donkey-kong-random-fireballs.html
	 * I created an algorithm by using information in that article.
	 */
	//Create movement for single fire elemental
	public void moveFireElemental(FireElemental fireElemental){
		boolean collision = false;
		boolean goUp = false;
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(fireElemental.getRectangle().getMinX() + 1, fireElemental.getRectangle().getMinY(),
				fireElemental.getRectangle().getMaxX() - fireElemental.getRectangle().getMinX() - 48, fireElemental.getRectangle().getMaxY() - fireElemental.getRectangle().getMinY())
				&& nonmovable.get(i).getRectangle().intersects(fireElemental.getRectangle().getMinX() + 49, fireElemental.getRectangle().getMinY(),
				fireElemental.getRectangle().getMaxX() - fireElemental.getRectangle().getMinX() - 48, fireElemental.getRectangle().getMaxY() - fireElemental.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				goUp = true;
				break;
			}
		}
		
		for(int i = 0; i < nonmovable.size(); i++){
			if(nonmovable.get(i).getRectangle().intersects(fireElemental.getRectangle().getMinX(), fireElemental.getRectangle().getMinY() + 1,
				fireElemental.getRectangle().getMaxX() - fireElemental.getRectangle().getMinX(), fireElemental.getRectangle().getMaxY() - fireElemental.getRectangle().getMinY())){
				collision = true;
			}
		}
		
		if(goUp && climbFireElemental(fireElemental) && collision){
			fireElemental.goUp();
		}
		else{
			if(!collision){
				fireElemental.gravity();
			}
			else{
				if(fireElemental.getRectangle().getMaxX() == 1000){ //Boundary points
					fireElemental.setDirection(Direction.LEFT);
				}
				else if(fireElemental.getRectangle().getMinX() == 0){ //Boundary points
					fireElemental.setDirection(Direction.RIGHT);
				}
				
				if(fireElemental.getDirection() == Direction.RIGHT){
					fireElemental.goRight();
				}
				else if(fireElemental.getDirection() == Direction.LEFT){
					fireElemental.goLeft();
				}
			}
		}
		
		collisionFireElementalAndPlayer(fireElemental);
	}
	
	//Check if the player dies or not.
	public boolean collisionBarrelAndPlayer(Barrel barrel){
		if(new Rectangle((int)(barrel.getRectangle().getMinX()) + 5, (int)(barrel.getRectangle().getMinY()) + 5, (int)(barrel.getRectangle().getMaxX() - barrel.getRectangle().getMinX()) - 10,
			(int)(barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY()) - 10).intersects(player.getRectangle().getMinX() + 10, player.getRectangle().getMinY() + 10,
			player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 20, player.getRectangle().getMaxY() - player.getRectangle().getMinY() - 20)){
			reloadMap();
			return true;
		}
		return false;
	}
	
	//Check if the player dies or not.
	public boolean collisionFireElementalAndPlayer(FireElemental fireElemental){
		if(new Rectangle((int)(fireElemental.getRectangle().getMinX()) + 5, (int)(fireElemental.getRectangle().getMinY()) + 5, (int)(fireElemental.getRectangle().getMaxX() - fireElemental.getRectangle().getMinX()) - 10,
			(int)(fireElemental.getRectangle().getMaxY() - fireElemental.getRectangle().getMinY()) - 10).intersects(player.getRectangle().getMinX() + 10, player.getRectangle().getMinY() + 10,
			player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 20, player.getRectangle().getMaxY() - player.getRectangle().getMinY() - 20)){
			reloadMap();
			return true;
		}
		return false;
	}
	
	//Remove the barrel if it hits a fire elemental
	public void collisionBarrelAndFireElemental(ArrayList<Barrel> barrels, ArrayList<FireElemental> fireElementals){
		try{
			for(int i = 0; i < barrels.size(); i++){
				for(int k = 0; k < fireElementals.size(); k++){
					if(barrels.get(i).getRectangle().intersects(fireElementals.get(k).getRectangle())){
						barrels.remove(i);
						break;
					}
				}
			}
		}
		catch(IndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
	
	//Collision
	//If barrel hits oil barrel. We will remove it from the enemies arrayList then instead add a fire elemental.
	public void collisionBarrelAndOil(Barrel barrel){
		if(oil.getRectangle().intersects(barrel.getRectangle().getMinX(), barrel.getRectangle().getMinY(), 
			barrel.getRectangle().getMaxX() - barrel.getRectangle().getMinX(), barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY())){
			oil.setLit(true);
			FireElemental fireElemental = new FireElemental(barrel.getX() / 50, barrel.getY() / 50);
			fireElementals.add(fireElemental);
			barrels.remove(barrel);
		}
	}
	
	/*
	 * Fall was originally written in assembly language in Donkey Kong 1981.
	 * Fortunately the code is analyzed in following article which is written by Don Hodges.
	 * http://donhodges.com/Controlling_the_barrels_in_Donkey_Kong.htm
	 * I created a similar algorithm to use.
	 */
	public boolean fallBarrel(Barrel barrel){
		if(!oil.getLit()){
			return true;
		}
		else{
			//Barrel checks whether player is climbing the exact same ladder
			if(barrel.getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() - 200,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY() + 150)){
				return true;
			}
			else if(barrel.getDirection() == Direction.RIGHT){
				if(player.getRectangle().intersects(barrel.getRectangle().getMinX(), barrel.getRectangle().getMinY() - 100, 
					1000 - barrel.getRectangle().getMinX(), barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY() + 100)){
					return false;
				}
				else{
					if(Math.random() > 0.75 || Math.random() < 0.25){
						return false;
					}
					else{
						return true;
					}
				}
			}
			else if(barrel.getDirection() == Direction.LEFT){
				if(player.getRectangle().intersects(0, barrel.getRectangle().getMinY() - 100, 
					barrel.getRectangle().getMaxX(), barrel.getRectangle().getMaxY() - barrel.getRectangle().getMinY() + 100)){
					return false;
				}
				else{
					if(Math.random() > 0.75 || Math.random() < 0.25){
						return false;
					}
					else{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean climbFireElemental(FireElemental fireElemental){
		if(player.getY() < fireElemental.getY()){
			return true;
		}
		return false;
	}

	public void wPressed(){
		boolean collision = false;
		boolean climb = false;
		for(int i = 0; i < nonmovable.size(); i++){
			//Collision with Platform
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() - 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !nonmovable.get(i).getPassThrough()){
				collision = true;
				break;
			}
			
			//Collision with Ladder
			if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 5, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 40, player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				climb = true;
				break;
			}
		}
		
		//Go up Ladder
		if(!collision && climb && !jump){
			player.goUp();
		}
		
		if(!collision && jumpable() && !climb){
			jump = true;
		}
	}
	
	//Checks whether or not the Jumpman can jump
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
	
	//Checks whether or not we can create a new barrel
	//This algorithm is important because it directly affect the gameplay.
	public boolean creatable(long second){
		if(BARREL_SPAWN_TIMER < second){
			return true;
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
				break;
			}
			
			//Collision with borders
			if(nonmovable.get(i).getRectangle().getMinX() < minX){
				minX = (int) nonmovable.get(i).getRectangle().getMinX();
			}
		}
		
		// Collision with borders
		if(player.getRectangle().getMinX() < 0 + 5){ //Boundary
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
				break;
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
				break;
			}
			
			//Collision with borders
			if(nonmovable.get(i).getRectangle().getMaxX() > maxX){
				maxX = (int) nonmovable.get(i).getRectangle().getMaxX();
			}
		}
		
		// Collision with borders
		if(player.getRectangle().getMaxX() > 1000 - 5){ //Boundary
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
				break;
			}
			//Player is climbing. Even though there is no collision, we do not want to use gravity on the Jumpman
			else if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 5, player.getRectangle().getMinY() + gravity,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX() + 40, player.getRectangle().getMinY() + gravity,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 40, player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& nonmovable.get(i) instanceof Ladder){
				collision = true;
				break;
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
				break;
			}
		}
		
		for(int i = 0; i < barrels.size(); i++){
			if(barrels.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY(),
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(),
				player.getRectangle().getMaxY() - player.getRectangle().getMinY() + 100) && barrels.get(i).isScorable()){
				score = score + 100;
				barrels.get(i).setScorable(false);
				break;
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
				break;
			}
			else if(nonmovable.get(i).getRectangle().intersects(player.getRectangle().getMinX(), player.getRectangle().getMinY() + 5,
				player.getRectangle().getMaxX() - player.getRectangle().getMinX(), player.getRectangle().getMaxY() - player.getRectangle().getMinY())
				&& !(nonmovable.get(i) instanceof Ladder)){
				jump = false;
				jumpSpeed = JUMP_SPEED_MAX;
				break;
			}
		}
	}
}
