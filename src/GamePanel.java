package source;

import java.awt.Color;
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

	public GamePanel(int level) throws FileNotFoundException{
		init(level);
	}
	
	public void init(int level) throws FileNotFoundException{
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
				//Draw the game
			}
			else if(!gameEngine.isMovement()){ //While game is paused
				//Pause menu initialization if needed
				//Draw pause menu or go pause menu, depends on initialization
			}
			
			try{
				Thread.sleep(500); //Rendering speed of the thread
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
		
		//Test
		g.fillRect(0, 0, 500, 500);
		g.setColor(Color.BLACK);
		g.drawString("TEST", (int)(Math.random()*(600-500)+500), (int)(Math.random()*(600-500)+500));
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
		inputMapPause.put(KeyStroke.getKeyStroke("released ESCAPE"), "escapePressed");
		actionMapPause.put("escapePressed", new KeyHandler("escape"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("W"), "wPressed");
		actionMapPause.put("wPressed", new KeyHandler("w"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("A"), "aPressed");
		actionMapPause.put("aPressed", new KeyHandler("a"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("D"), "dPressed");
		actionMapPause.put("dPressed", new KeyHandler("d"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("S"), "sPressed");
		actionMapPause.put("sPressed", new KeyHandler("s"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("SPACE"), "spacePressed");
		actionMapPause.put("spacePressed", new KeyHandler("space"));
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
					System.out.print("Movement is ");
					System.out.println(gameEngine.isMovement());
				}
				else if(!gameEngine.isMovement()){ //While game is paused
					resume();
					System.out.print("Movement is ");
					System.out.println(gameEngine.isMovement());
				}
			}
			else if(name == "w"){
				System.out.println("W Pressed");
				if(gameEngine.isMovement()){ //While game is running
					gameEngine.wPressed();
					System.out.println("Go Up or Jump");
				}
			}
			else if(name == "a"){
				System.out.println("A Pressed");
				if(gameEngine.isMovement()){ //While game is running
					gameEngine.aPressed();
					System.out.println("Go Left");
				}
			}
			else if(name == "s"){
				System.out.println("S Pressed");
				if(gameEngine.isMovement()){ //While game is running
					gameEngine.sPressed();
					System.out.println("Go Down");
				}
			}
			else if(name == "d"){
				System.out.println("D Pressed");
				if(gameEngine.isMovement()){ //While game is running
					gameEngine.dPressed();
					System.out.println("Go Right");
				}
			}
			else if(name == "space"){
				System.out.println("Space Pressed");
				if(gameEngine.isMovement()){ //While game is running
					gameEngine.spacePressed();
					System.out.println("Strike");
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
