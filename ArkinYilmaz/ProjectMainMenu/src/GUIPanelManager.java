package source;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIPanelManager extends JFrame {
	
	private JPanel currentPanel = null;
	private Controller controller = null;
	
	public  GUIPanelManager() {
		
		super("Donkey Kong");
		
		setMainMenuPanelVisible();
		
		setSize(1000,1000);
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

		setContentPane(newPanel);
		currentPanel = new MainMenu(this);
		
		this.setVisible(true);
	}
	
	public void setOptionsPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new OptionsPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setHighScoresPanelVisible() throws IOException {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new HighScoresPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setLevelSelectionPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new LevelSelectionPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
		
		this.setVisible(true);
	}
	
	public void setGamePanelVisible(int level) throws IOException {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		GamePanel newPanel;
		GameEngine gameEngine;
		try {
			newPanel = new GamePanel(this);
			gameEngine = new GameEngine(level);
			controller = new Controller(newPanel, gameEngine, this);
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

}