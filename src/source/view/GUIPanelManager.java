package source.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import source.controller.Controller;
import source.controller.Sound;
import source.model.GameEngine;

@SuppressWarnings("serial")
public class GUIPanelManager extends JFrame {
	
	private JPanel currentPanel = null;
	private Controller controller = null;
	private Sound sound = null;
	
	public  GUIPanelManager() {		
		super("Donkey Kong");
		
		sound = new Sound();
		
		setMainMenuPanelVisible();
		
		setSize(1005,1025);
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}
	
	public void setMainMenuPanelVisible() {
		if(controller != null){
			controller = null;
		}
		
		if (currentPanel != null){
			currentPanel.setVisible(false);
		}
		
		JPanel newPanel = new MainMenu(this);
		
		sound.stopBackground();
		sound.stopLevelSelection();
		if(sound.sound()){
			sound.background();
		}
		
		setContentPane(newPanel);
		currentPanel = new MainMenu(this);
		
		this.setVisible(true);
	}
	
	public void setOptionsPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new OptionsPanel(this, sound);
		
		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setHighScoresPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = null;
		try {
			newPanel = new HighScoresPanel(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setLevelSelectionPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new LevelSelectionPanel(this);

		sound.stopBackground();		
		if(sound.sound()){
			sound.levelSelection();
		}
		
		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setGamePanelVisible(int level) {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		sound.stopLevelSelection();
		GamePanel newPanel;
		GameEngine gameEngine;
		try {
			newPanel = new GamePanel(this);
			gameEngine = new GameEngine(level);
			controller = new Controller(newPanel, gameEngine, sound, this);
			setContentPane(newPanel);
			currentPanel = newPanel;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setVisible(true);
	}
	
	
	public void setHelpPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new HelpPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setCreditsPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new CreditsPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}

	public void setEndGamePanelVisible(int score) {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new EndGamePanel(this, score);

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
}