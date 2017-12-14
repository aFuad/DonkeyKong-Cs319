package source;

import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIPanelManager extends JFrame {
	
	private JPanel currentPanel = null;
	private Controller controller = null;
	
	public  GUIPanelManager() {
		
		super("Donkey Kong");
		
		setMainMenuPanelVisible();
		
		setSize(1050,1050);
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
	
	public void setHighScoresPanelVisible() {
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
	
	public void setGamePanelVisible(int level) {
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

}