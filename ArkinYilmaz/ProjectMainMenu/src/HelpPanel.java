package source;

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

public class HelpPanel extends JPanel{
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel, helpLeftRight, helpUpDown, helpJump, helpSmashHammer, helpPause, tips;
	private int currentOption = 0;
	
	public HelpPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;
		
		helpLeftRight = new JLabel("<html><p>PRESS A AND D TO MOVE LEFT OR RIGHT</p></html>");
		helpLeftRight.setForeground(MainMenu.strColor);
		helpLeftRight.setFont(new Font("Press Start 2P", Font.BOLD,50));
		helpLeftRight.setSize(900, 150);
		helpLeftRight.setLocation(100, 80);
		add(helpLeftRight);
		
		helpUpDown = new JLabel("<html><p>PRESS W AND S TO CLIMB UP OR DOWN A LADDER</p></html>");
		helpUpDown.setForeground(MainMenu.strColor);
		helpUpDown.setFont(new Font("Press Start 2P", Font.BOLD,50));
		helpUpDown.setSize(900, 150);
		helpUpDown.setLocation(100, 80);
		add(helpUpDown);
		
		helpJump = new JLabel("<html><p>PRESS W TO JUMP</p></html>");
		helpJump.setForeground(MainMenu.strColor);
		helpJump.setFont(new Font("Press Start 2P", Font.BOLD,50));
		helpJump.setSize(900, 150);
		helpJump.setLocation(100, 30);
		add(helpJump);
		
		helpSmashHammer = new JLabel("<html><p>PRESS SPACE TO SMASH BARRELS</p></html>");
		helpSmashHammer.setForeground(MainMenu.strColor);
		helpSmashHammer.setFont(new Font("Press Start 2P", Font.BOLD,50));
		helpSmashHammer.setSize(900, 150);
		helpSmashHammer.setLocation(100, 55);
		add(helpSmashHammer);
		
		helpPause = new JLabel("<html><p>PRESS ESC TO PAUSE THE GAME</p></html>");
		helpPause.setForeground(MainMenu.strColor);
		helpPause.setFont(new Font("Press Start 2P", Font.BOLD,50));
		helpPause.setSize(900, 150);
		helpPause.setLocation(100, 55);
		add(helpPause);
		
		/*tips = new JLabel("PRESS ESC TO PAUSE THE GAME");
		tips.setForeground(MainMenu.strColor);
		tips.setFont(MainMenu.optionsFont);
		tips.setSize(285, 20);
		tips.setLocation(355, 850);
		add(tips);*/
		
		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.highlighter);
		returnLabel.setFont(MainMenu.optionsFont);
		returnLabel.setSize(285, 45);
		returnLabel.setLocation(355, 850);
		add(returnLabel);
		
		helpLeftRight.setVisible(true);
		helpUpDown.setVisible(false);
		helpJump.setVisible(false);
		helpSmashHammer.setVisible(false);
		helpPause.setVisible(false);
		returnLabel.setVisible(true);
		
		
		setBackground(Color.BLACK);
		setLayout(null);
	}
	
	
	public void initializeKeyBindings(){
		InputMap inputMapPause = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMapPause = this.getActionMap();
		
		inputMapPause.put(KeyStroke.getKeyStroke("released ENTER"), "enterReleased");
		actionMapPause.put("enterReleased", new KeyHandler("enter"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("A"), "aPressed");
		actionMapPause.put("aPressed", new KeyHandler("a"));
		
		inputMapPause.put(KeyStroke.getKeyStroke("D"), "dPressed");
		actionMapPause.put("dPressed", new KeyHandler("d"));
		
	}
	
	class KeyHandler extends AbstractAction{
		String name;
		
		public KeyHandler(String name){
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(name == "enter"){
				guiManager.setOptionsPanelVisible();	
			}
			
			else if(name == "a") {
				if(currentOption > 5)
					currentOption = 5;
				
				currentOption--;
				if(currentOption == 0) {
					helpLeftRight.setVisible(true);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					
				}
				else if(currentOption == 1) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(true);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 2) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(true);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 3) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(true);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 4) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(true);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 5) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				
			}
			
			else if(name == "d") {
				if(currentOption < 0)
					currentOption = 0;
				
				currentOption++;
				if(currentOption == 0) {
					helpLeftRight.setVisible(true);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					
				}
				else if(currentOption == 1) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(true);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 2) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(true);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 3) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(true);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 4) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(true);
					returnLabel.setVisible(true);
				}
				else if(currentOption == 5) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
				}
			}
		}
	}
}