package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class GamePanel extends JPanel{
	private GUIPanelManager guiPanelManager;
	
	private ArrayList<Nonmovable> nonmovable;
	private ArrayList<Barrel> barrels;
	private ArrayList<FireElemental> fireElementals;
	private Player player;
	private JLabel scoreLabel;
	private Image heart = new ImageIcon("src/image/heart.png").getImage();
	private Image brokenHeart = new ImageIcon("src/image/brokenHeart.png").getImage();
	private int score;
	private int remainingLife;
	
	public GamePanel(GUIPanelManager guiPanelManager) throws FileNotFoundException{
		this.guiPanelManager = guiPanelManager;
		
		scoreLabel = new JLabel();
		add(scoreLabel);
	}
	
	public void notified(ArrayList<Nonmovable> nonmovable, ArrayList<Barrel> barrels, ArrayList<FireElemental> fireElementals, Player player, int score, int remainingLife){
		this.nonmovable = nonmovable;
		this.barrels = barrels;
		this.fireElementals = fireElementals;
		this.player = player;
		this.score = score;
		this.remainingLife = remainingLife;
		repaint();
	}
	
	public void returnBackToMainMenu(){
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
		boolean oilDisplay = false;
		boolean girlDisplay = false;
		boolean monkeyDisplay = false;
		super.paintComponent(g);
		setBackground(Color.BLACK);
		
		scoreLabel.setForeground(MainMenu.strColor);
		scoreLabel.setFont(new Font("Press Start 2P", Font.BOLD,20));
		scoreLabel.setSize(400, 40);
		scoreLabel.setLocation(750, 10);
		scoreLabel.setText("SCORE:" + score);
		
		for(int i = 0; i < remainingLife; i++) {
			g.drawImage(heart, 750 + (i * 50), 40, this);
		}
		
		for(int i =  3 - remainingLife; i > 0; i--) {
			g.drawImage(brokenHeart, 900 - (i * 50), 40, this);
		}
		
		for(int i = 0; i < nonmovable.size(); i++){
			if(!(nonmovable.get(i) instanceof Monkey) && !(nonmovable.get(i) instanceof Girl) && !(nonmovable.get(i) instanceof Oil)){
				g.drawImage(nonmovable.get(i).getImage(), nonmovable.get(i).getX(), nonmovable.get(i).getY(), this);
			}
			else if(nonmovable.get(i) instanceof Oil){
				if(!oilDisplay){
					g.drawImage(nonmovable.get(i).getImage(), nonmovable.get(i).getX(), nonmovable.get(i).getY(), this);
					oilDisplay = true;
				}
			}
			else if(nonmovable.get(i) instanceof Girl){
				if(!girlDisplay){
					g.drawImage(nonmovable.get(i).getImage(), nonmovable.get(i).getX(), nonmovable.get(i).getY(), this);
					girlDisplay = true;
				}
			}
			else if(nonmovable.get(i) instanceof Monkey){
				if(!monkeyDisplay){
					g.drawImage(nonmovable.get(i).getImage(), nonmovable.get(i).getX(), nonmovable.get(i).getY(), this);
					monkeyDisplay = true;
				}
			}
		}
		
		for(int i = 0; i < barrels.size(); i++){
			g.drawImage(barrels.get(i).getImage(), barrels.get(i).getX(), barrels.get(i).getY(), this);
			//g.drawRect((int)(barrels.get(i).getRectangle().getMinX()), (int)(barrels.get(i).getRectangle().getMinY()) + 50, (int)(barrels.get(i).getRectangle().getMaxX() - barrels.get(i).getRectangle().getMinX()) - 45, (int)(barrels.get(i).getRectangle().getMaxY() - barrels.get(i).getRectangle().getMinY()) - 45);
			//g.drawRect((int)(barrels.get(i).getRectangle().getMinX()) + 45, (int)(barrels.get(i).getRectangle().getMaxY()), (int)(barrels.get(i).getRectangle().getMaxX() - barrels.get(i).getRectangle().getMinX()) - 45, (int)(barrels.get(i).getRectangle().getMaxY() - barrels.get(i).getRectangle().getMinY()) - 45);
		}
		
		for(int i = 0; i < fireElementals.size(); i++){
			g.drawImage(fireElementals.get(i).getImage(), fireElementals.get(i).getX(), fireElementals.get(i).getY(), this);
			//g.drawRect((int)(fireElementals.get(i).getRectangle().getMinX()), (int)(fireElementals.get(i).getRectangle().getMinY()), (int)(fireElementals.get(i).getRectangle().getMaxX() - fireElementals.get(i).getRectangle().getMinX()), (int)(fireElementals.get(i).getRectangle().getMaxY() - fireElementals.get(i).getRectangle().getMinY()));
		}
		
		if(player.getHammer()){
			g.drawImage(player.getImage(), player.getX(), player.getHammerY(), this);
		}
		else{
			g.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}
		//Comment below aim to test rectangle of player
		g.drawRect((int)(player.getRectangle().getMinX()), (int)(player.getRectangle().getMinY()), (int)(player.getRectangle().getMaxX() - player.getRectangle().getMinX()), (int)(player.getRectangle().getMaxY() - player.getRectangle().getMinY()) - 20);
		g.drawRect((int)(player.getRectangle().getMinX()), (int)(player.getRectangle().getMinY()) + 20, (int)(player.getRectangle().getMaxX() - player.getRectangle().getMinX()), (int)(player.getRectangle().getMaxY() - player.getRectangle().getMinY()) - 40);
	}
}
