import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
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
	private JLabel returnLabel, level1, level2, level3, l1img, l2img, l3img;
	private BufferedImage level1img, level2img, level3img;
	private int currentOption;
	private int level = 1;
	
	private Border whiteBorder = BorderFactory.createLineBorder(Color.white);
	private Border orangeBorder = BorderFactory.createLineBorder(MainMenu.highlighter);
	
	public LevelSelectionPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;

		try {
			level1img = ImageIO.read(new File("C:\\Users\\user\\Desktop\\level1img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		l1img = new JLabel(new ImageIcon(level1img));
		l1img.setSize(280, 230);
		l1img.setBorder(orangeBorder);
		l1img.setLocation(130, 40);
		add(l1img);
		
		try {
			level2img = ImageIO.read(new File("C:\\Users\\user\\Desktop\\level1img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		l2img = new JLabel(new ImageIcon(level2img));
		l2img.setSize(280, 230);
		l2img.setBorder(whiteBorder);
		l2img.setLocation(130, 290);
		add(l2img);
		
		try {
			level3img = ImageIO.read(new File("C:\\Users\\user\\Desktop\\level1img.png"));
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
		
		inputMapPause.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "enterPressed");
		actionMapPause.put("enterPressed", new KeyHandler("enter"));
		
		inputMapPause.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), "upPressed");
		actionMapPause.put("upPressed", new KeyHandler("up"));
		
		inputMapPause.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "downPressed");
		actionMapPause.put("downPressed", new KeyHandler("down"));
		
	}
	
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(name == "enter"){
				if(currentOption == 0) {
					System.out.println("Level1 is selected");
					level = 1;
					//guiManager.setGamePanelVisible(level);
				}
				else if(currentOption == 1) {
					System.out.println("Level2 is selected");
					level = 2;
					//guiManager.setGamePanelVisible(level);
				}
				else if(currentOption == 2) {
					System.out.println("Level3 is selected");
					level = 3;
					//guiManager.setGamePanelVisible(level);
				}
				if(currentOption == 3) {
					guiManager.setMainMenuPanelVisible();
					System.out.println("Return(LevelSelection) pressed");
				}
			}
			
			else if(name == "up") {
				currentOption--;
	        	
	        	if(currentOption == 0) {
	        		level2.setForeground(MainMenu.strColor);
	        		l2img.setBorder(whiteBorder);
	        		level1.setForeground(MainMenu.highlighter);
	        		l1img.setBorder(orangeBorder);
	        	}
	        	
	        	else if(currentOption == 1) {
	        		level3.setForeground(MainMenu.strColor);
	        		l3img.setBorder(whiteBorder);
	     			level2.setForeground(MainMenu.highlighter);
	     			l2img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			returnLabel.setForeground(MainMenu.strColor);
	     			level3.setForeground(MainMenu.highlighter);
	     			l3img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == 3){
	     			level1.setForeground(MainMenu.strColor);
	     			l1img.setBorder(whiteBorder);
	     			returnLabel.setForeground(MainMenu.highlighter);
		     	}
	     		
	        	//check bounds
        		if(currentOption < 0) {
        			currentOption = 3;
        			level1.setForeground(MainMenu.strColor);
        			l1img.setBorder(whiteBorder);
        			returnLabel.setForeground(MainMenu.highlighter);
        		}
			}
			
			else if(name == "down") {
				currentOption++;
	        	
	        	if(currentOption == 0) {
	        		returnLabel.setForeground(MainMenu.strColor);
	        		level1.setForeground(MainMenu.highlighter);
	        		l1img.setBorder(orangeBorder);
	        	}
	        		
	        	else if(currentOption == 1) {
	        		level1.setForeground(MainMenu.strColor);
	        		l1img.setBorder(whiteBorder);
	        		level2.setForeground(MainMenu.highlighter);
	        		l2img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			level2.setForeground(MainMenu.strColor);
	     			l2img.setBorder(whiteBorder);
	     			level3.setForeground(MainMenu.highlighter);
	     			l3img.setBorder(orangeBorder);
	     		}
	        	
	     		else if(currentOption == 3){
	     			level3.setForeground(MainMenu.strColor);
	     			l3img.setBorder(whiteBorder);
	     			returnLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	        	//check bounds
	        	if(currentOption > 3) {
	        		currentOption = 0;
	        		returnLabel.setForeground(MainMenu.strColor);
	        		level1.setForeground(MainMenu.highlighter);
	        		l1img.setBorder(orangeBorder);
	        	}
			}
		}
	}	

}
