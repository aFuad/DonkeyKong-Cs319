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
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class HighScoresPanel extends JPanel {
	
	private GUIPanelManager guiManager;
	private JLabel returnLabel;
	private int currentOption;

	public HighScoresPanel(GUIPanelManager guiManager) {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		currentOption = 0;

		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.highlighter);
		returnLabel.setFont(MainMenu.optionsFont);
		returnLabel.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		inputMapPause.put(KeyStroke.getKeyStroke("released ENTER"), "enterReleased");
		actionMapPause.put("enterReleased", new KeyHandler("enter"));
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
				}
			}
		}
	}	
}