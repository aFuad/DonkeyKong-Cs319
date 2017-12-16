package source.view;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;


public class HelpPanel extends JPanel{
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel, helpLeftRight, helpUpDown, helpJump, helpSmashHammer, helpPause, tips;
	private int currentOption = 0;
	private JLabel label1, label2, label3, label4, label5;
	private BufferedImage label1img, label2img, label3img, label4img, label5img;
	public HelpPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;
		
		currentOption = 0;
		
		initializeKeyBindings();

		try {
			label1img = ImageIO.read(new File("src/image/a.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label1 = new JLabel(new ImageIcon(label1img));
		label1.setSize(500, 500);
		label1.setLocation(240, 260);
		add(label1);
		
		try {
			label2img = ImageIO.read(new File("src/image/b.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label2 = new JLabel(new ImageIcon(label2img));
		label2.setSize(500, 500);
		label2.setLocation(240, 250);
		add(label2);
		
		try {
			label3img = ImageIO.read(new File("src/image/c.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label3 = new JLabel(new ImageIcon(label3img));
		label3.setSize(600, 600);
		label3.setLocation(200, 200);
		add(label3);
		
		try {
			label4img = ImageIO.read(new File("src/image/d.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label4 = new JLabel(new ImageIcon(label4img));
		label4.setSize(600, 600);
		label4.setLocation(200, 200);
		add(label4);
		
		try {
			label5img = ImageIO.read(new File("src/image/e.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label5 = new JLabel(new ImageIcon(label5img));
		label5.setSize(500, 500);
		label5.setLocation(240, 240);
		add(label5);
		
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
		label1.setVisible(true);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		
		
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
				if(currentOption > 4)
					currentOption = 4;
				
				currentOption--;
				if(currentOption == 0) {
					helpLeftRight.setVisible(true);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(true);
					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(false);
	
				}
				else if(currentOption == 1) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(true);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(true);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(false);
				}
				else if(currentOption == 2) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(true);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(true);
					label4.setVisible(false);
					label5.setVisible(false);
				}
				else if(currentOption == 3) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(true);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(true);
					label5.setVisible(false);
				}
				else if(currentOption == 4) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(true);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(true);
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
					label1.setVisible(true);
					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(false);
				}
				else if(currentOption == 1) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(true);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(true);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(false);
				}
				else if(currentOption == 2) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(true);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(true);
					label4.setVisible(false);
					label5.setVisible(false);
				}
				else if(currentOption == 3) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(true);
					helpPause.setVisible(false);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(true);
					label5.setVisible(false);
				}
				else if(currentOption == 4) {
					helpLeftRight.setVisible(false);
					helpUpDown.setVisible(false);
					helpJump.setVisible(false);
					helpSmashHammer.setVisible(false);
					helpPause.setVisible(true);
					returnLabel.setVisible(true);
					label1.setVisible(false);
					label2.setVisible(false);
					label3.setVisible(false);
					label4.setVisible(false);
					label5.setVisible(true);
				}
			}
		}
	}
	
}