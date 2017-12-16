package source;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class CreditsPanel extends JPanel {
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel, creditsLabel1, creditsLabel2, creditsLabel3, creditsLabel4, creditsLabel5;
	private int currentOption = 0;
	
	public CreditsPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;
		
		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.highlighter);
		returnLabel.setFont(MainMenu.optionsFont);
		returnLabel.setSize(285, 45);
		returnLabel.setLocation(355, 850);
		add(returnLabel);
		
		String text = "";
		text= "<html><span style='font-size:40px'>CS 319 <br/>Object-Oriented Software <br/>Engineering <br/>Fall 2017</span></html>";
		creditsLabel1 = new JLabel(text);
		creditsLabel1.setFont(MainMenu.optionsFont);
		creditsLabel1.setForeground(MainMenu.strColor);
		creditsLabel1.setSize(1050, 300);
		creditsLabel1.setLocation(100, 100);
		add(creditsLabel1);
		
		String text2="";
		text2= "<html><span style='font-size:30px'>Cagatay Kupeli</span></html>";
		
		creditsLabel2 = new JLabel(text2);
		creditsLabel2.setForeground(MainMenu.strColor);
		creditsLabel2.setFont(MainMenu.optionsFont);
		creditsLabel2.setSize(800, 500);
		creditsLabel2.setLocation(100, 200);
		add(creditsLabel2);
		
		String text3 = "<html><span style='font-size:30px'>Arkin Yilmaz</span></html>";
		creditsLabel3 = new JLabel(text3);
		creditsLabel3.setForeground(MainMenu.strColor);
		creditsLabel3.setFont(MainMenu.optionsFont);
		creditsLabel3.setSize(800, 500);
		creditsLabel3.setLocation(100, 250);
		add(creditsLabel3);
		
		String text4 = "<html><span style='font-size:30px'>Fuad Ahmed</span></html>";		
		creditsLabel4 = new JLabel(text4);
		creditsLabel4.setForeground(MainMenu.strColor);
		creditsLabel4.setFont(MainMenu.optionsFont);
		creditsLabel4.setSize(800, 500);
		creditsLabel4.setLocation(100, 300);
		add(creditsLabel4);
		
		String text5 = "<html><span style='font-size:30px'>Sine Mete<br/><br/></span><span style='font-size:20px'>Instructor: Bora Gungoren</span></html>";
		creditsLabel5 = new JLabel(text5);
		creditsLabel5.setForeground(MainMenu.strColor);
		creditsLabel5.setFont(MainMenu.optionsFont);
		creditsLabel5.setSize(800, 500);
		creditsLabel5.setLocation(100, 380);
		add(creditsLabel5);
		
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
					guiManager.setOptionsPanelVisible();	
				}
			}
		}
	}
}