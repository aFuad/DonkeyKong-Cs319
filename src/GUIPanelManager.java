package source;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIPanelManager extends JFrame {
	
	private JPanel currentPanel = null;
	
	public  GUIPanelManager() {
		
		super("Donkey Kong");
		
		setMainMenuPanelVisible();
		
		setSize(1005,1030);
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setMainMenuPanelVisible() {
		if (currentPanel != null)
			currentPanel.setVisible(false);

		JPanel newPanel = new MainMenu(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
	}
	
	public void setOptionsPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new OptionsPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
	}
	
	public void setHighScoresPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new HighScorePanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
	}
	
	public void setLevelSelectionPanelVisible() {
		if(currentPanel != null)
			currentPanel.setVisible(false);
		
		JPanel newPanel = new LevelSelectionPanel(this);

		setContentPane(newPanel);
		currentPanel = newPanel;
	}

}