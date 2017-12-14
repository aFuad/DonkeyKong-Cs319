package source;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class LevelSelectionPanel extends JPanel {
	private GUIPanelManager guiManager;
	private UnlockData unlockData;
	
	private JLabel returnLabel, level1, level2, level3, l1img, l2img, l3img;
	private BufferedImage level1img, level2img, level3img;
	private int currentOption;
	private final int MAX_LEVEL_NUMBER = 3;
	private final int RETURN_LABEL = 4;
	private int level;
	private int unlock = 3;
	
	private Border whiteBorder = BorderFactory.createLineBorder(Color.white);
	private Border orangeBorder = BorderFactory.createLineBorder(MainMenu.highlighter);
	
	public LevelSelectionPanel(GUIPanelManager guiManager) {
		this.guiManager = guiManager;
		try {
			this.unlockData = new UnlockData();
			unlock = unlockData.getUnlock();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			guiManager.setMainMenuPanelVisible();
		}
		
		currentOption = 1;
		
		initializeKeyBindings();

		try {
			level1img = ImageIO.read(new File("src/image/18.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		l1img = new JLabel(new ImageIcon(level1img));
		l1img.setSize(280, 230);
		l1img.setBorder(orangeBorder);
		l1img.setLocation(130, 40);
		add(l1img);
		
		try {
			level2img = ImageIO.read(new File("src/image/18.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		l2img = new JLabel(new ImageIcon(level2img));
		l2img.setSize(280, 230);
		l2img.setBorder(whiteBorder);
		l2img.setLocation(130, 290);
		add(l2img);
		
		try {
			level3img = ImageIO.read(new File("src/image/18.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		l3img = new JLabel(new ImageIcon(level3img));
		l3img.setSize(280, 230);
		l3img.setBorder(whiteBorder);
		l3img.setLocation(130, 540);
		add(l3img);
		
		level1 = new JLabel("LEVEL1");
		level1.setForeground(MainMenu.highlighter);
		level1.setFont(MainMenu.optionsFont);
		level1.setSize(300, 45);
		level1.setLocation(550, 125);
		add(level1);
		
		level2 = new JLabel("LEVEL2");
		level2.setForeground(MainMenu.strColor);
		level2.setFont(MainMenu.optionsFont);
		level2.setSize(300, 45);
		level2.setLocation(550, 375);
		add(level2);
		
		level3 = new JLabel("LEVEL3");
		level3.setForeground(MainMenu.strColor);
		level3.setFont(MainMenu.optionsFont);
		level3.setSize(300, 45);
		level3.setLocation(550, 625);
		add(level3);

		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.strColor);
		returnLabel.setFont(MainMenu.optionsFont);
		returnLabel.setSize(285, 45);
		returnLabel.setLocation(355, 850);
		add(returnLabel);
		
		setBackground(Color.BLACK);
		setLayout(null);	
	}
	
	public void initializeKeyBindings(){
		InputMap inputMapPause = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMapPause = this.getActionMap();
		
		inputMapPause.put(KeyStroke.getKeyStroke("released ENTER"), "enterReleased");
		actionMapPause.put("enterReleased", new KeyHandler("enter"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("W"), "wPressed");
		actionMapPause.put("wPressed", new KeyHandler("w"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("S"), "sPressed");
		actionMapPause.put("sPressed", new KeyHandler("s"));
		
	}
	
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(name == "enter"){
				if(currentOption == 1) {
					level = 1;
					guiManager.setGamePanelVisible(level);
				}
				else if(currentOption == 2) {
					level = 2;
					guiManager.setGamePanelVisible(level);
				}
				else if(currentOption == 3) {
					level = 3;
					guiManager.setGamePanelVisible(level);
				}
				else if(currentOption == RETURN_LABEL) {
					guiManager.setMainMenuPanelVisible();
				}
			}
			
			else if(name == "w") {
				if(currentOption <= unlock || unlock == MAX_LEVEL_NUMBER){
					currentOption--;
				}
				else{
					currentOption = unlock;
				}
	        	
	        	if(currentOption == 1) {
	        		level2.setForeground(MainMenu.strColor);
	        		l2img.setBorder(whiteBorder);
	        		level1.setForeground(MainMenu.highlighter);
	        		l1img.setBorder(orangeBorder);
	        		returnLabel.setForeground(MainMenu.strColor);
	        	}
	        	
	        	else if(currentOption == 2) {
	        		level3.setForeground(MainMenu.strColor);
	        		l3img.setBorder(whiteBorder);
	     			level2.setForeground(MainMenu.highlighter);
	     			l2img.setBorder(orangeBorder);
	     			returnLabel.setForeground(MainMenu.strColor);
	     		}
	        	
	     		else if(currentOption == 3) {
	     			returnLabel.setForeground(MainMenu.strColor);
	     			level3.setForeground(MainMenu.highlighter);
	     			l3img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == RETURN_LABEL){
	     			level1.setForeground(MainMenu.strColor);
	     			l1img.setBorder(whiteBorder);
	     			returnLabel.setForeground(MainMenu.highlighter);
		     	}
	     		
	        	//check bounds
        		if(currentOption < 1) {
        			currentOption = RETURN_LABEL;
        			level1.setForeground(MainMenu.strColor);
        			l1img.setBorder(whiteBorder);
        			returnLabel.setForeground(MainMenu.highlighter);
        		}
			}
			
			else if(name == "s") {
				if(currentOption < unlock || currentOption == RETURN_LABEL || unlock == MAX_LEVEL_NUMBER){
					currentOption++;
				}
				else{
					currentOption = RETURN_LABEL;
				}
	        	
	        	if(currentOption == 1) {
	        		returnLabel.setForeground(MainMenu.strColor);
	        		level1.setForeground(MainMenu.highlighter);
	        		l1img.setBorder(orangeBorder);
	        	}
	        		
	        	else if(currentOption == 2) {
	        		level1.setForeground(MainMenu.strColor);
	        		l1img.setBorder(whiteBorder);
	        		level2.setForeground(MainMenu.highlighter);
	        		l2img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == 3) {
	     			level2.setForeground(MainMenu.strColor);
	     			l2img.setBorder(whiteBorder);
	     			level3.setForeground(MainMenu.highlighter);
	     			l3img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == RETURN_LABEL){
	     			level1.setForeground(MainMenu.strColor);
	     			l1img.setBorder(whiteBorder);
	     			level2.setForeground(MainMenu.strColor);
	     			l2img.setBorder(whiteBorder);
	     			level3.setForeground(MainMenu.strColor);
	     			l3img.setBorder(whiteBorder);
	     			returnLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	        	//check bounds
	        	if(currentOption > RETURN_LABEL) {
	        		currentOption = 1;
	        		returnLabel.setForeground(MainMenu.strColor);
	        		level1.setForeground(MainMenu.highlighter);
	        		l1img.setBorder(orangeBorder);
	        	}
			}
		}
	}	

}