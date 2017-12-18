package source.controller;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import source.model.GameEngine;
import source.view.GUIPanelManager;
import source.view.GamePanel;

public class Controller implements Runnable{
	private final long FRAME_PER_SECOND = 1000 / 60; // 60 FPS
	
	private Thread myThread;
	private GameEngine gameEngine;
	private GamePanel gamePanel;
	private GUIPanelManager guiPanelManager;
	private Sound sound;
	
	private boolean buttonW, buttonA, buttonS, buttonD, buttonSpace = false;

	public Controller(GamePanel gamePanel, GameEngine gameEngine, Sound sound, GUIPanelManager guiPanelManager) throws FileNotFoundException{
		init(gamePanel, gameEngine, sound, guiPanelManager);
	}
	
	private void init(GamePanel gamePanel, GameEngine gameEngine, Sound sound, GUIPanelManager guiPanelManager) throws FileNotFoundException{
		this.gamePanel = gamePanel;
		this.gameEngine = gameEngine;
		this.sound = sound;
		this.guiPanelManager = guiPanelManager;
		
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
		long secondBarrel = 0; //Important for calculating the last time a new barrel has spawned
		long secondObject = 0; //Used for update girl and oil animation
		long secondMonkey = 0; //Used for update monkey animation
		long secondHammer = 0; //Used for update hammer animation
		while(!gameEngine.isGameOver()){ //While game is not over
			gamePanel.setPause(false);
			if(gameEngine.isMovement()){ //While game is running
				//Display
				gamePanel.notified(gameEngine.getMapObjects(), gameEngine.getBarrelList(), gameEngine.getFireElementalList(), gameEngine.getPlayer(), gameEngine.getScore() + gameEngine.getTotalScore(), gameEngine.getRemainingLives());
				
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
				
				if(!gameEngine.getPlayer().isStrike()){
					secondHammer = 0;
				}
				
				//Notify monkey to change their animations
				if(gameEngine.updateMonkeyTimer(secondMonkey)){
					gameEngine.updateMonkey();
					secondMonkey = 0;
				}
				
				//Notify player to change the position of hammer
				if(gameEngine.hammerTimer(secondHammer)){
					gameEngine.updateHammer();
				}
				
				//Create a new enemy barrel
				if(gameEngine.creatable(secondBarrel)){
					gameEngine.createBarrel(gameEngine.getBarrelSpawnX(), gameEngine.getBarrelSpawnY(), gameEngine.rollEnemyType());
					secondBarrel = 0;
				}
				
				//Notify each object to change their animations
				if(gameEngine.updateObjectTimer(secondObject)){
					gameEngine.updateObjects();
					secondObject = 0;
				}
				
				//Load next level or return back to menu
				if(gameEngine.collisionWithFinishPoint()){
					try {
						if(!gameEngine.loadNextLevel(gameEngine.getLevel() + 1)){
							gameEngine.setGameOver(true);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					secondBarrel = 0;
					secondObject = 0;
					secondMonkey = 0;
					secondHammer = 0;
				}
				
				if(gameEngine.getPlayer().getHammer()){
					gameEngine.collisionStrike();
				}
				
				for(int i = 0; i < gameEngine.getBarrelList().size(); i++){
					if(gameEngine.moveBarrel(gameEngine.getBarrelList().get(i))){
						secondBarrel = 0;
						secondObject = 0;
						secondMonkey = 0;
						secondHammer = 0;
					}
				}
				
				for(int i = 0; i < gameEngine.getFireElementalList().size(); i++){
					if(gameEngine.moveFireElemental(gameEngine.getFireElementalList().get(i))){
						secondBarrel = 0;
						secondObject = 0;
						secondMonkey = 0;
						secondHammer = 0;
					}
				}
				
				gameEngine.collisionBarrelAndFireElemental(gameEngine.getBarrelList(), gameEngine.getFireElementalList());
				
				gameEngine.collisionHammerAndPlayer();
				
				gameEngine.collisionCoinAndPlayer();
				
				gameEngine.collisionExtraLifeAndPlayer();
				
				if(gameEngine.getRemainingLives() == 0){
					gameEngine.setGameOver(true);
					gameEngine.setMovement(false);
				}
			}
			else if(!gameEngine.isMovement()){ //While game is paused
				//Bug fix
				buttonW = false;
				buttonA = false;
				buttonS = false;
				buttonD = false;
				buttonSpace = false;
				
				secondBarrel = 0;
				secondObject = 0;
				secondMonkey = 0;
				secondHammer = 0;
				//Pause menu initialization if needed
				gamePanel.setPause(true);
				//Draw pause menu or go pause menu, depends on initialization
			}
			
			try{
				Thread.sleep(FRAME_PER_SECOND);
			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
			secondBarrel = secondBarrel + FRAME_PER_SECOND;
			secondObject = secondObject + FRAME_PER_SECOND;
			secondMonkey = secondMonkey + FRAME_PER_SECOND;
			secondHammer = secondHammer + FRAME_PER_SECOND;
		}
		//Go back to main menu
		if(gameEngine.updateHighScore(gameEngine.getTotalScore())){
			guiPanelManager.setEndGamePanelVisible(gameEngine.getTotalScore());
		}
		else{
			guiPanelManager.setMainMenuPanelVisible();
		}
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
	@SuppressWarnings("serial")
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(name == "escape"){
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
					if(!sound.soundWalkOn()){
						sound.walk();
					}
				}
			}
			else if(name == "aReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonA = false;
					sound.stopWalk();
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
					if(!sound.soundWalkOn()){
						sound.walk();
					}
				}
			}
			else if(name == "dReleased"){
				if(gameEngine.isMovement()){ //While game is running
					buttonD = false;
					sound.stopWalk();
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
}
