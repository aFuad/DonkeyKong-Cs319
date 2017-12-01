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
	private ArrayList<ArrayList<Nonmovable>> nonmovable;
	private ArrayList<Enemy> barrels;
	private ArrayList<FireElemental> fireElementals;
	private Player player;
	
	public GamePanel(GUIPanelManager guiPanelManager) throws FileNotFoundException{
		this.guiPanelManager = guiPanelManager;
	}
	
	public void notified(ArrayList<ArrayList<Nonmovable>> nonmovable, ArrayList<Enemy> barrels, ArrayList<FireElemental> fireElementals, Player player){
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
		for(int y = 0; y < nonmovable.size(); y++){
			ArrayList<Nonmovable> innerList = nonmovable.get(y);
			ArrayList<Nonmovable> innerListNextRow = null;
			if(y < nonmovable.size() - 1){
				innerListNextRow = nonmovable.get(y + 1);
			}
			for(int x = 0; x < innerList.size(); x++){
				if(innerList.get(x) instanceof Platform){
					g.drawImage(innerList.get(x).getImage(), innerList.get(x).getX(), innerList.get(x).getY(), this);
				}
				else if(innerList.get(x) instanceof Ladder){
					g.drawImage(innerList.get(x).getImage(), innerList.get(x).getX(), innerList.get(x).getY(), this);
				}
				else if(innerListNextRow != null){
					if(innerList.get(x) instanceof Girl && innerListNextRow.get(x) instanceof Girl){
						g.drawImage(innerList.get(x).getImage(), innerList.get(x).getX(), innerList.get(x).getY(), this);
					}
					else if(innerList.get(x) instanceof Oil && innerListNextRow.get(x) instanceof Oil){
						g.drawImage(innerList.get(x).getImage(), innerList.get(x).getX(), innerList.get(x).getY(), this);
					}
					else if(innerList.get(x) instanceof Monkey && innerList.get(x + 1) instanceof Monkey
						&& innerListNextRow.get(x) instanceof Monkey && innerListNextRow.get(x + 1) instanceof Monkey){
						g.drawImage(innerList.get(x).getImage(), innerList.get(x).getX(), innerList.get(x).getY(), this);
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
		g.drawRect((int)(player.getRectangle().getMinX() + 5), (int)(player.getRectangle().getMinY()), (int)(player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 45), (int)(player.getRectangle().getMaxX() - player.getRectangle().getMinX()));
		g.drawRect((int)(player.getRectangle().getMinX() + 40), (int)(player.getRectangle().getMinY()), (int)(player.getRectangle().getMaxX() - player.getRectangle().getMinX() - 45), (int)(player.getRectangle().getMaxX() - player.getRectangle().getMinX()));
	}
}
