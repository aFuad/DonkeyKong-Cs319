import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HighScoresPanel extends JPanel {
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel, titleLabel , one, two, three, four, five;
	private int currentOption;

	public HighScoresPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;
		
		titleLabel = new JLabel("HIGHSCORES");
		titleLabel.setForeground(MainMenu.strColor);
		titleLabel.setFont(new Font("Press Start 2P", Font.BOLD,60));
		titleLabel.setSize(650, 100);
		titleLabel.setLocation(180, 40);
		add(titleLabel);
		
		one = new JLabel("1.");
		one.setForeground(MainMenu.strColor);
		one.setFont(new Font("Press Start 2P", Font.BOLD,60));
		one.setSize(285, 60);
		one.setLocation(100, 200);
		add(one);
		
		two = new JLabel("2.");
		two.setForeground(MainMenu.strColor);
		two.setFont(new Font("Press Start 2P", Font.BOLD,60));
		two.setSize(285, 60);
		two.setLocation(100, 330);
		add(two);
		
		three = new JLabel("3.");
		three.setForeground(MainMenu.strColor);
		three.setFont(new Font("Press Start 2P", Font.BOLD,60));
		three.setSize(285, 60);
		three.setLocation(100, 460);
		add(three);
		
		four = new JLabel("4.");
		four.setForeground(MainMenu.strColor);
		four.setFont(new Font("Press Start 2P", Font.BOLD,60));
		four.setSize(285, 60);
		four.setLocation(100, 590);
		add(four);
		
		five = new JLabel("5.");
		five.setForeground(MainMenu.strColor);
		five.setFont(new Font("Press Start 2P", Font.BOLD,60));
		five.setSize(285, 60);
		five.setLocation(100, 720);
		add(five);

		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.highlighter);
		returnLabel.setFont(MainMenu.optionsFont);
		returnLabel.setSize(285, 45);
		returnLabel.setLocation(355, 850);
		add(returnLabel);
		
		//setPreferredSize(new Dimension(750,750));
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
					guiManager.setMainMenuPanelVisible();
					System.out.println("Return(HighScores) pressed");
				}
			}
			/*
			else if(name == "up") {
				currentOption--;
	        	
	        	if(currentOption == 0) {
	        		options.setForeground(strColor);
	        		play.setForeground(highlighter);
	        	}
	        	
	        	else if(currentOption == 1) {
	        		highscores.setForeground(strColor);
	     			options.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			quit.setForeground(strColor);
	     			highscores.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 3){
	     			play.setForeground(strColor);
	     			quit.setForeground(highlighter);
		     	}
	     		
	        	//check bounds
        		if(currentOption < 0) {
        			currentOption = 3;
        			play.setForeground(strColor);
        			quit.setForeground(highlighter);
        		}
			}
			
			else if(name == "down") {
				currentOption++;
	        	
	        	if(currentOption == 0) {
	        		quit.setForeground(strColor);
	        		play.setForeground(highlighter);
	        	}
	        		
	        	else if(currentOption == 1) {
	        		play.setForeground(strColor);
	        		options.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			options.setForeground(strColor);
	     			highscores.setForeground(highlighter);
	     		}
	        	
	     		else if(currentOption == 3){
	     			highscores.setForeground(strColor);
	     			quit.setForeground(highlighter);
	     		}
	        	
	        	//check bounds
	        	if(currentOption > 3) {
	        		currentOption = 0;
	        		quit.setForeground(strColor);
	        		play.setForeground(highlighter);
	        	}
			}*/	
		}
	}	
}
