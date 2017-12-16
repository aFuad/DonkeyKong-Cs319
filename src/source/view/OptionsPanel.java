package source.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import source.controller.Sound;


@SuppressWarnings("serial")
public class OptionsPanel extends JPanel{
	
	private GUIPanelManager guiManager;
	
	private JLabel returnLabel, titleLabel, soundLabel, helpLabel, creditsLabel;
	private int currentOption;
	protected static boolean soundOn = true;
	private Sound sound;
	
	public OptionsPanel(GUIPanelManager guiManager, Sound sound) {
		initializeKeyBindings();
		
		this.guiManager = guiManager;
		this.sound = sound;
		currentOption = 0;
		
		titleLabel = new JLabel("OPTIONS");
		titleLabel.setForeground(MainMenu.strColor);
		titleLabel.setFont(new Font("Press Start 2P", Font.BOLD,60));
		titleLabel.setSize(650, 100);
		titleLabel.setLocation(280, 40);
		add(titleLabel);
		
		if(soundOn) {
			soundLabel = new JLabel("SOUND:ON");
		}
		else {
			soundLabel = new JLabel("SOUND:OFF");
		}
		
		soundLabel.setForeground(MainMenu.highlighter);
		soundLabel.setFont(MainMenu.optionsFont);
		soundLabel.setSize(450, 60);
		soundLabel.setLocation(100, 250);
		add(soundLabel);
		
		helpLabel = new JLabel("HELP");
		helpLabel.setForeground(MainMenu.strColor);
		helpLabel.setFont(MainMenu.optionsFont);
		helpLabel.setSize(285, 60);
		helpLabel.setLocation(100, 380);
		add(helpLabel);
		
		creditsLabel = new JLabel("SHOW CREDITS");
		creditsLabel.setForeground(MainMenu.strColor);
		creditsLabel.setFont(MainMenu.optionsFont);
		creditsLabel.setSize(600, 60);
		creditsLabel.setLocation(100, 510);
		add(creditsLabel);
		
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
				if(currentOption == 0) {
					if(soundLabel.getText().equals("SOUND:ON")) {
						soundOn = false;
						soundLabel.setText("SOUND:OFF");
						sound.disableSound();
					}
					else {
						soundOn = true;
						soundLabel.setText("SOUND:ON");
						sound.enableSound();
					}
				}
				else if(currentOption == 1) {
					guiManager.setHelpPanelVisible();
				}
				else if(currentOption == 2) {
					guiManager.setCreditsPanelVisible();
				}
				else if(currentOption == 3) {
					guiManager.setMainMenuPanelVisible();
				}
			}
			
			else if(name == "w") {
				currentOption--;
	        	
	        	if(currentOption == 0) {
	        		helpLabel.setForeground(MainMenu.strColor);
	        		soundLabel.setForeground(MainMenu.highlighter);
	        	}
	        	
	        	else if(currentOption == 1) {
	        		creditsLabel.setForeground(MainMenu.strColor);
	        		helpLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			returnLabel.setForeground(MainMenu.strColor);
	     			creditsLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	     		else if(currentOption == 3){
	     			soundLabel.setForeground(MainMenu.strColor);
	     			returnLabel.setForeground(MainMenu.highlighter);
		     	}
	     		
	        	//check bounds
        		if(currentOption < 0) {
        			currentOption = 3;
        			soundLabel.setForeground(MainMenu.strColor);
        			returnLabel.setForeground(MainMenu.highlighter);
        		}
			}
			
			else if(name == "s") {
				currentOption++;
	        	
	        	if(currentOption == 0) {
	        		returnLabel.setForeground(MainMenu.strColor);
	        		soundLabel.setForeground(MainMenu.highlighter);
	        	}
	        		
	        	else if(currentOption == 1) {
	        		soundLabel.setForeground(MainMenu.strColor);
	        		helpLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	     		else if(currentOption == 2) {
	     			helpLabel.setForeground(MainMenu.strColor);
	     			creditsLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	     		else if(currentOption == 3){
	     			creditsLabel.setForeground(MainMenu.strColor);
	     			returnLabel.setForeground(MainMenu.highlighter);
	     		}
	        	
	        	//check bounds
	        	if(currentOption > 3) {
	        		currentOption = 0;
	        		returnLabel.setForeground(MainMenu.strColor);
	        		soundLabel.setForeground(MainMenu.highlighter);
	        	}
			}	
		}
	}	
}
