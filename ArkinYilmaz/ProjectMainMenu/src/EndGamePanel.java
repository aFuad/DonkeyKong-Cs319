package source;

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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;


public class EndGamePanel extends JPanel{
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel, titleLabel;
	private JTextField textArea;
	private int currentOption = 0;
	
	public EndGamePanel(GUIPanelManager guiManager) {
		initializeKeyBindings();
		this.guiManager = guiManager;
		
		titleLabel = new JLabel("ENTER NAME");
		titleLabel.setForeground(MainMenu.strColor);
		titleLabel.setFont(new Font("Press Start 2P", Font.BOLD,60));
		titleLabel.setSize(700, 150);
		titleLabel.setLocation(180, 40);
		add(titleLabel);
		
		textArea = new JTextField();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(MainMenu.strColor);
		textArea.setFont(new Font("Press Start 2P", Font.BOLD,120));
		textArea.setHorizontalAlignment(SwingConstants.CENTER);
		textArea.setBorder(null);
		textArea.setSize(800, 600);
		textArea.setLocation(90, 200);
		textArea.requestFocus();
		add(textArea);

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
					textArea.requestFocus();
				}
				
				else if(currentOption == 1) {
					guiManager.setMainMenuPanelVisible();
					System.out.println("Return(HighScores) pressed");
				}
			}
			
			else if(name == "up") {
				currentOption--;
	        	
	        	if(currentOption == 0) {
	        		textArea.setEditable(true);
	        		textArea.setFocusable(true);
	        		textArea.requestFocus();
	        		returnLabel.setForeground(MainMenu.strColor);
	        	}
	        	
	        	else if(currentOption == 1) {
	        		textArea.setEditable(false);
	        		textArea.setFocusable(false);
	        		returnLabel.setForeground(MainMenu.highlighter);
	        	}
	        	
			}
			
			else if(name == "down") {
				currentOption++;
	        	
	        	if(currentOption == 0) {
	        		textArea.setEditable(true);
	        		textArea.setFocusable(true);
	        		textArea.requestFocus();
	        		returnLabel.setForeground(MainMenu.strColor);
	        	}
	        	
	        	else if(currentOption == 1) {
	        		textArea.setEditable(false);
	        		textArea.setFocusable(false);
	        		returnLabel.setForeground(MainMenu.highlighter);
	        	}
			}	
		}
	}

}
