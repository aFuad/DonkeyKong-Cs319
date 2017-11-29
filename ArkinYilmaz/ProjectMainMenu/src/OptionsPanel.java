import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class OptionsPanel extends JPanel{
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel;
	private int currentOption;
	//private final int GRID_HEIGHT = 6;
	//private final int GRID_WIDTH = 1;

	public OptionsPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;
		
		
		
		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.highlighter);
		returnLabel.setFont(MainMenu.optionsFont);
		//returnLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
					System.out.println("Return(Options) pressed");
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
