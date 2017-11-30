package source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel{
	private GUIPanelManager guiPanelManager;
	Nonmovable [][] nonmovable = null;
	ArrayList<Enemy> barrels = null;
	ArrayList<FireElemental> fireElementals = null;
	Player player = null;
	
	public GamePanel(GUIPanelManager guiPanelManager) throws FileNotFoundException{
		this.guiPanelManager = guiPanelManager;
	}
	
	public void notified(Nonmovable[][] nonmovable, ArrayList<Enemy> barrels, ArrayList<FireElemental> fireElementals, Player player){
		this.nonmovable = nonmovable;
		this.barrels = barrels;
		this.fireElementals = fireElementals;
		this.player = player;
		repaint();
	}
	
	public void returnMainMenu(){
		guiPanelManager.setMainMenuPanelVisible();
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
		setBackground(Color.BLACK);
		
		//Test
		for(int y = 0; y < 20; y++){
			for(int x = 0; x < 20; x++){
				//Commented part below aim to test rectangles of nonmovable objects
				if(nonmovable[x][y] instanceof Platform){
					g.drawImage(nonmovable[x][y].getImage(), nonmovable[x][y].getX(), nonmovable[x][y].getY(), this);
				}
				else if(nonmovable[x][y] instanceof Ladder){
					g.drawImage(nonmovable[x][y].getImage(), nonmovable[x][y].getX(), nonmovable[x][y].getY(), this);
				}
				else if(y < 19){ //If y = 19, then inside the if statement we check y = 20 and program gives outOfBound error
					if(nonmovable[x][y] instanceof Girl && nonmovable[x][y + 1] instanceof Girl){ //To create girl, we need 2 blocks because monkey takes 50x100 space
						g.drawImage(nonmovable[x][y].getImage(), nonmovable[x][y].getX(), nonmovable[x][y].getY(), this);
					}
					else if(nonmovable[x][y] instanceof Oil && nonmovable[x][y + 1] instanceof Oil){ //To create oil, we need 2 blocks because monkey takes 50x100 space
						g.drawImage(nonmovable[x][y].getImage(), nonmovable[x][y].getX(), nonmovable[x][y].getY(), this);
					}
					else if(x < 19){ //If x = 19, then inside the if statement we check x = 20 and program gives outOfBound error.
						//To create monkey, we need 4 blocks because monkey takes 100x100 space
						if(nonmovable[x][y] instanceof Monkey && nonmovable[x + 1][y] instanceof Monkey && nonmovable[x][y + 1] instanceof Monkey && nonmovable[x + 1][y + 1] instanceof Monkey){
							g.drawImage(nonmovable[x][y].getImage(), nonmovable[x][y].getX(), nonmovable[x][y].getY(), this);
						}
					}
				}
			}
		}
		for(int i = 0; i < barrels.size(); i++){
			g.drawImage(barrels.get(i).getImage(), barrels.get(i).getX(), barrels.get(i).getY(), this);
		}
		
		for(int i = 0; i < fireElementals.size(); i++){
			g.drawImage(fireElementals.get(i).getImage(), fireElementals.get(i).getX(), fireElementals.get(i).getY(), this);
		}
		
		g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		//Comment below aim to test rectangle of player
		//g.drawRect((int)(gameEngine.getPlayer().getRectangle().getMinX() + 15), (int)(gameEngine.getPlayer().getRectangle().getMinY()), (int)(gameEngine.getPlayer().getRectangle().getMaxX() - gameEngine.getPlayer().getRectangle().getMinX() - 40), (int)(gameEngine.getPlayer().getRectangle().getMaxX() - gameEngine.getPlayer().getRectangle().getMinX()));
	}
}
