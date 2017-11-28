package source;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel implements Runnable{
	private Thread myThread;
	private GameEngine gameEngine;
	
	private boolean buttonW, buttonA, buttonS, buttonD, buttonSpace = false;

	public GamePanel(int level) throws FileNotFoundException{
		init(level);
	}
	
	private void init(int level) throws FileNotFoundException{
		gameEngine = new GameEngine(level);
		/*
		 * start() generates thread for GamePanel class.
		 * I made a method because of we might want to use structure of this class again in some other class such as PauseMenu.
		 */
		start();
		
		/*
		 * initializeKeyBindings() decides which keyboard inputs we are going to use and as in the name initialize them
		 * Same reason as start() method. We might want to use KeyHandler inner class and this method in other GUI parts such as MainMenu.
		 */
		initializingKeyBindings();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!gameEngine.isGameOver()){ //While game is not over
			if(gameEngine.isMovement()){ //While game is running
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
					//System.out.println(gameEngine.isJump());
				}
				gameEngine.gravity();
			}
			else if(!gameEngine.isMovement()){ //While game is paused
				//Pause menu initialization if needed
				//Draw pause menu or go pause menu, depends on initialization
			}
			//gameEngine.sPressed();
			
			try{
				Thread.sleep(20); //Rendering speed of the thread 100
			}
			catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
		//Go back to main menu
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 * 
	 * When we call repaint() in overriden run() method, it calls paintComponent() method and render the JPanel.
	 * This method initialize after object methods because we need to call drawImage by using those objects.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//setBackground(Color.BLACK);
		//Test
		for(int y = 0; y < 20; y++){
			for(int x = 0; x < 20; x++){
				//Commented part below aim to test rectangles of nonmovable objects
				if(gameEngine.getMapObject(x, y) instanceof Platform){
					g.drawImage(gameEngine.getMapObject(x, y).getImage(), gameEngine.getMapObject(x, y).getX(), gameEngine.getMapObject(x, y).getY(), this);
					//g.drawRect((int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMinY(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxX() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxY() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinY());
				}
				else if(gameEngine.getMapObject(x, y) instanceof Ladder){
					g.drawImage(gameEngine.getMapObject(x, y).getImage(), gameEngine.getMapObject(x, y).getX(), gameEngine.getMapObject(x, y).getY(), this);
					//g.drawRect((int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMinY(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxX() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxY() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinY());
				}
				else if(y < 19){ //If y = 19, then inside the if statement we check y = 20 and program gives outOfBound error
					if(gameEngine.getMapObject(x, y) instanceof Girl && gameEngine.getMapObject(x, y + 1) instanceof Girl){ //To create girl, we need 2 blocks because monkey takes 50x100 space
						g.drawImage(gameEngine.getMapObject(x, y).getImage(), gameEngine.getMapObject(x, y).getX(), gameEngine.getMapObject(x, y).getY(), this);
						//g.drawRect((int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMinY(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxX() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxY() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinY());
					}
					else if(gameEngine.getMapObject(x, y) instanceof Oil && gameEngine.getMapObject(x, y + 1) instanceof Oil){ //To create oil, we need 2 blocks because monkey takes 50x100 space
						g.drawImage(gameEngine.getMapObject(x, y).getImage(), gameEngine.getMapObject(x, y).getX(), gameEngine.getMapObject(x, y).getY(), this);
						//g.drawRect((int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMinY(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxX() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxY() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinY());
					}
					else if(x < 19){ //If x = 19, then inside the if statement we check x = 20 and program gives outOfBound error.
						//To create monkey, we need 4 blocks because monkey takes 100x100 space
						if(gameEngine.getMapObject(x, y) instanceof Monkey && gameEngine.getMapObject(x + 1, y) instanceof Monkey && gameEngine.getMapObject(x, y + 1) instanceof Monkey && gameEngine.getMapObject(x + 1, y + 1) instanceof Monkey){
							g.drawImage(gameEngine.getMapObject(x, y).getImage(), gameEngine.getMapObject(x, y).getX(), gameEngine.getMapObject(x, y).getY(), this);
							//g.drawRect((int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMinY(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxX() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinX(), (int) gameEngine.getMapObject(x, y).getRectangle().getMaxY() - (int) gameEngine.getMapObject(x, y).getRectangle().getMinY());
						}
					}
				}
			}
		}
		
		g.drawImage(gameEngine.getPlayer().getImagePlayerRight(), gameEngine.getPlayer().getX(), gameEngine.getPlayer().getY(), this);
		//Comment below aim to test rectangle of player
		//g.drawRect((int)(gameEngine.getPlayer().getRectangle().getMinX()), (int)(gameEngine.getPlayer().getRectangle().getMinY()), (int)(gameEngine.getPlayer().getRectangle().getMaxX() - gameEngine.getPlayer().getRectangle().getMinX()), (int)(gameEngine.getPlayer().getRectangle().getMaxX() - gameEngine.getPlayer().getRectangle().getMinX()));
	}
	
	public void initializingKeyBindings(){
		InputMap inputMapPause = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMapPause = this.getActionMap();
		
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
