package source.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import source.controller.ScoreData;

@SuppressWarnings("serial")
public class HighScoresPanel extends JPanel {
	
	private GUIPanelManager guiManager;
	private ScoreData highscores;
	private JLabel returnLabel, titleLabel , one, two, three, four, five;
	private JLabel firstName, secondName, thirdName, fourthName, fifthName;
	private JLabel firstScore, secondScore, thirdScore, fourthScore, fifthScore;

	public HighScoresPanel(GUIPanelManager guiManager) throws IOException {
		
		initializeKeyBindings();
		this.guiManager = guiManager;
		highscores = new ScoreData();
		
		titleLabel = new JLabel("HIGHSCORES");
		titleLabel.setForeground(MainMenu.strColor);
		titleLabel.setFont(new Font("Press Start 2P", Font.BOLD,60));
		titleLabel.setSize(650, 100);
		titleLabel.setLocation(180, 40);
		add(titleLabel);
		
		one = new JLabel("1.");
		one.setForeground(MainMenu.strColor);
		one.setFont(new Font("Press Start 2P", Font.BOLD,60));
		one.setSize(285, 60);
		one.setLocation(80, 200);
		add(one);
		
		firstName = new JLabel(highscores.getName(4));
		firstName.setForeground(MainMenu.strColor);
		firstName.setFont(new Font("Press Start 2P", Font.BOLD,60));
		firstName.setSize(350, 60);
		firstName.setLocation(190, 200);
		add(firstName);
 
		firstScore = new JLabel(Integer.toString(highscores.getScore(4)));
		firstScore.setForeground(MainMenu.strColor);
		firstScore.setFont(new Font("Press Start 2P", Font.BOLD,60));
		firstScore.setSize(350, 60);
		firstScore.setLocation(600, 200);
		add(firstScore);
		
		two = new JLabel("2.");
		two.setForeground(MainMenu.strColor);
		two.setFont(new Font("Press Start 2P", Font.BOLD,60));
		two.setSize(285, 60);
		two.setLocation(80, 330);
		add(two);

		secondName = new JLabel(highscores.getName(3));
		secondName.setForeground(MainMenu.strColor);
		secondName.setFont(new Font("Press Start 2P", Font.BOLD,60));
		secondName.setSize(350, 60);
		secondName.setLocation(190, 330);
		add(secondName);
		
		secondScore = new JLabel(Integer.toString(highscores.getScore(3)));
		secondScore.setForeground(MainMenu.strColor);
		secondScore.setFont(new Font("Press Start 2P", Font.BOLD,60));
		secondScore.setSize(350, 60);
		secondScore.setLocation(600, 330);
		add(secondScore);
		
		three = new JLabel("3.");
		three.setForeground(MainMenu.strColor);
		three.setFont(new Font("Press Start 2P", Font.BOLD,60));
		three.setSize(285, 60);
		three.setLocation(80, 460);
		add(three);
		
		thirdName = new JLabel(highscores.getName(2));
		thirdName.setForeground(MainMenu.strColor);
		thirdName.setFont(new Font("Press Start 2P", Font.BOLD,60));
		thirdName.setSize(350, 60);
		thirdName.setLocation(190, 460);
		add(thirdName);
		
		thirdScore = new JLabel(Integer.toString(highscores.getScore(2)));
		thirdScore.setForeground(MainMenu.strColor);
		thirdScore.setFont(new Font("Press Start 2P", Font.BOLD,60));
		thirdScore.setSize(350, 60);
		thirdScore.setLocation(600, 460);
		add(thirdScore);
		
		four = new JLabel("4.");
		four.setForeground(MainMenu.strColor);
		four.setFont(new Font("Press Start 2P", Font.BOLD,60));
		four.setSize(285, 60);
		four.setLocation(80, 590);
		add(four);
		
		fourthName = new JLabel(highscores.getName(1));
		fourthName.setForeground(MainMenu.strColor);
		fourthName.setFont(new Font("Press Start 2P", Font.BOLD,60));
		fourthName.setSize(350, 60);
		fourthName.setLocation(190, 590);
		add(fourthName);
		
		fourthScore = new JLabel(Integer.toString(highscores.getScore(1)));
		fourthScore.setForeground(MainMenu.strColor);
		fourthScore.setFont(new Font("Press Start 2P", Font.BOLD,60));
		fourthScore.setSize(350, 60);
		fourthScore.setLocation(600, 590);
		add(fourthScore);
		
		five = new JLabel("5.");
		five.setForeground(MainMenu.strColor);
		five.setFont(new Font("Press Start 2P", Font.BOLD,60));
		five.setSize(285, 60);
		five.setLocation(80, 720);
		add(five);
		
		fifthName = new JLabel(highscores.getName(0));
		fifthName.setForeground(MainMenu.strColor);
		fifthName.setFont(new Font("Press Start 2P", Font.BOLD,60));
		fifthName.setSize(350, 60);
		fifthName.setLocation(190, 720);
		add(fifthName);
		
		fifthScore = new JLabel(Integer.toString(highscores.getScore(0)));
		fifthScore.setForeground(MainMenu.strColor);
		fifthScore.setFont(new Font("Press Start 2P", Font.BOLD,60));
		fifthScore.setSize(350, 60);
		fifthScore.setLocation(600, 720);
		add(fifthScore);

		returnLabel = new JLabel("RETURN");
		returnLabel.setForeground(MainMenu.highlighter);
		returnLabel.setFont(MainMenu.optionsFont);
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
				guiManager.setMainMenuPanelVisible();
			}
		}
	}	
}