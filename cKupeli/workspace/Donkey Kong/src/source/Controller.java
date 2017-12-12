package source;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Controller implements Runnable{
	private final long FRAME_PER_SECOND = 1000 / 60; // 30 FPS
	
	private Thread myThread;
	private GameEngine gameEngine;
	private GamePanel gamePanel;
	
	private boolean buttonW, buttonA, buttonS, buttonD, buttonSpace = false;

	public Controller(GamePanel gamePanel, GameEngine gameEngine) throws FileNotFoundException{
		init(gamePanel, gameEngine);
	}
	
	private void init(GamePanel gamePanel, GameEngine gameEngine) throws FileNotFoundException{
		this.gamePanel = gamePanel;
		this.gameEngine = gameEngine;
		
		/*
		 * initializeKeyBindings() decides which keyboard inputs we are going to use and as in the name initialize them
		 * Same reason as start() method. We might want to use KeyHandler inner class and this method in other GUI parts such as MainMenu.
		 */
		initializingKeyBindings(gamePanel);
		
		/*
		 * start() generates thread for GamePanel class.
		 * I made a method because of we might want to use structure of this class again in some other class such as PauseMenu.
		 */
		start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long second = 0; //Important for calculating the last time a new barrel has spawned
		while(!gameEngine.isGameOver()){ //While game is not over
			if(gameEngine.isMovement()){ //While game is running
				//Update
				//Keybindings boolean values
				if(buttonW){
					gameEngine.wPressed();
				}
				
				if(buttonA){
					gameEngine.aPressed();
				}
				
				if(buttonS){
					gameEngine.sPressed();
				}
				
				if(buttonD){
					gameEngine.dPressed();
				}
				
				if(buttonSpace){
					gameEngine.spacePressed();
				}
				
				if(gameEngine.isJump()){
					gameEngine.jump();
				}
				gameEngine.gravity();
				
				//Create a new enemy barrel
				if(gameEngine.creatable(second)){
					gameEngine.createBarrel(gameEngine.getBarrelSpawnX(), gameEngine.getBarrelSpawnY(), gameEngine.rollEnemyType());
					second = 0;
				}
				
				for(int i = 0; i < gameEngine.getBarrelList().size(); i++){
					gameEngine.moveBarrel(gameEngine.getBarrelList().get(i));
				}
				
				for(int i = 0; i < gameEngine.getFireElementalList().size(); i++){
					gameEngine.moveFireElemental(gameEngine.getFireElementalList().get(i));
				}
				
				if(gameEngine.getRemainingLives() == 0){
					stop();
				}
				
				gameEngine.collisionBarrelAndFireElemental(gameEngine.getBarrelList(), gameEngine.getFireElementalList());
				
				//Display
				gamePanel.notified(gameEngine.getMapObjects(), gameEngine.getBarrelList(), gameEngine.getFireElementalList(), gameEngine.getPlayer());
			}
			else if(!gameEngine.isMovement()){ //While game is paused
				//Bug fix
				buttonW = false;
				buttonA = false;
				buttonS = false;
				buttonD = false;
				buttonSpace = false;
				//Pause menu initialization if needed
				//Draw pause menu or go pause menu, depends on initialization
			}
			
			try{
				Thread.sleep(FRAME_PER_SECOND);
			}
			catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			second = second + FRAME_PER_SECOND;
			System.out.println("Score: " + gameEngine.getScore());
			System.out.println("Lives: " + gameEngine.getRemainingLives());
		}
		//Go back to main menu
		gamePanel.goBackToMainMenu();
	}
	
	public void initializingKeyBindings(GamePanel gamePanel){
		InputMap inputMapPause = gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMapPause = gamePanel.getActionMap();
		
		/*
		 * 1st parameter is the name of the keyboard input.
		 * ESCAPE = ESC button
		 * W = W button
		 * A = A button
		 * S = S button
		 * D = D button
		 * SPACE = Space Button
		 * 2nd parameter is the name of the action which indicates pressing a button which we will use with ActionMap object.
		 */
		
		/* 
		 * We decided to use release for ESCAPE to prevent overlapping.
		 * We want player to press once to pause or resume the game and released is a perfect fit for that action.
		 */
		inputMapPause.put(KeyStroke.getKeyStroke("released ESCAPE"), "escapeReleased");
		actionMapPause.put("escapeReleased", new KeyHandler("escape"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("W"), "wPressed");
		actionMapPause.put("wPressed", new KeyHandler("wPressed"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("released W"), "wReleased");
		actionMapPause.put("wReleased", new KeyHandler("wReleased"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("A"), "aPressed");
		actionMapPause.put("aPressed", new KeyHandler("aPressed"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("released A"), "aReleased");
		actionMapPause.put("aReleased", new KeyHandler("aReleased"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("D"), "dPressed");
		actionMapPause.put("dPressed", new KeyHandler("dPressed"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("released D"), "dReleased");
		actionMapPause.put("dReleased", new KeyHandler("dReleased"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("S"), "sPressed");
		actionMapPause.put("sPressed", new KeyHandler("sPressed"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("released S"), "sReleased");
		actionMapPause.put("sReleased", new KeyHandler("sReleased"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("SPACE"), "spacePressed");
		actionMapPause.put("spacePressed", new KeyHandler("spacePressed"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("released SPACE"), "spaceReleased");
		actionMapPause.put("spaceReleased", new KeyHandler("spaceReleased"));
	}
	
	//Nested class to implement KeyBindings
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(name == "escape"){
				System.out.println("Escape Pressed");
				if(gameEngine.isMovement()){ //While game is running
					pause();
				}
				else if(!gameEngine.isMovement()){ //While game is paused
					resume();
				}
			}
			else if(name == "wPressed"){
				if(gameEngine.isMovement()){ //While game is running
					buttonW = true;
				}
			}
			else if(name == "wReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonW = false;
				}
			}
			else if(name == "aPressed"){
				if(gameEngine.isMovement()){ //While game is running
					buttonA = true;
				}
			}
			else if(name == "aReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonA = false;
				}
			}
			else if(name == "sPressed"){
				if(gameEngine.isMovement()){ //While game is running
					buttonS = true;
				}
			}
			else if(name == "sReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonS = false;
				}
			}
			else if(name == "dPressed"){
				if(gameEngine.isMovement()){ //While game is running
					buttonD = true;
				}
			}
			else if(name == "dReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonD = false;
				}
			}
			else if(name == "spacePressed"){
				if(gameEngine.isMovement()){ //While game is running
					buttonSpace = true;
				}
			}
			else if(name == "spaceReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonSpace = false;
				}
			}
			
		}
		
	}
	
	private void start(){
		myThread = new Thread(this);
		myThread.start();
	}
	
	private void pause(){
		gameEngine.setMovement(false);
	}
	
	private void resume(){
		gameEngine.setMovement(true);
	}
	
	private void stop(){
		gameEngine.setGameOver(true);
		gameEngine.setMovement(false);
	}
}
