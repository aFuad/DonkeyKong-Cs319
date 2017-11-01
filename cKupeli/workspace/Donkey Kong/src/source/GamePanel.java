package source;

import java.io.FileNotFoundException;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	private Thread myThread;
	
	private GameEngine gameEngine;

	public GamePanel(int level) throws FileNotFoundException {
		gameEngine = new GameEngine(level);
		
		myThread = new Thread(this);
		myThread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!gameEngine.isGameOver()){ //While game is not over
			if(gameEngine.isMovement()){ //While game is running
				/* KeyListeners will be added later
				 * There will be another if statement in this segment of the code.
				 * When the user pressed appropriate KeyListeners, the code will call pause() method.
				 * Thus, it will set the movement false and the loop won't execute this segment of the code.
				 * if(keyListener pressed){
				 *	 pause();
				 * }
				 */
			}
			else if(!gameEngine.isMovement()){ //While game is paused
				/* KeyListeners will be added later
				 * There will be another if statement in this segment of the code.
				 * When the user pressed appropriate KeyListeners, the code will call resume() method.
				 * Thus, it will set the movement true and the loop won't execute this segment of the code.
				 * if(keyListener pressed){
				 *	 resume();
				 * }
				 */
			}
			
			try{
				Thread.sleep(500); //Rendering speed of the thread
			}
			catch (InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void pause(){
		gameEngine.setMovement(false);
	}
	
	public void resume(){
		gameEngine.setMovement(true);
	}
	
	public void stop(){
		gameEngine.setGameOver(true);
	}
	
}
